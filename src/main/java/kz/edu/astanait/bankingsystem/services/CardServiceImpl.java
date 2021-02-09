package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.exceptions.CardNotFoundException;
import kz.edu.astanait.bankingsystem.exceptions.UserNotFoundException;
import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.ExchangeRates;
import kz.edu.astanait.bankingsystem.models.Transaction;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.CardRepository;
import kz.edu.astanait.bankingsystem.repositories.TransactionRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import kz.edu.astanait.bankingsystem.services.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Card> getCardsByPhoneNumber(String phoneNumber) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
        return cardRepository.findCardsByUser(user);
    }

    @Transactional
    public void makeTransaction(Long senderId, Long receiverId, Double amount) {
        Card sender = cardRepository.findById(senderId).orElseThrow(() -> new CardNotFoundException("Sender's card not found"));
        sender.setBalanceKzt(sender.getBalanceKzt() - amount);

        if (receiverId != null) {
            Card receiver = cardRepository.findById(receiverId).orElseThrow(() -> new CardNotFoundException("Receiver's card not found"));
            receiver.setBalanceKzt(receiver.getBalanceKzt() + amount);
        }

        transactionRepository.save(new Transaction(amount, sender));
    }

    @Transactional
    public void convert(Long cardId, String sentCurrency, String receivedCurrency, Double amount) {
        if (sentCurrency.equals(receivedCurrency))
            return;

        String sentAndReceivedCurrencies = sentCurrency + "-" + receivedCurrency;

        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Card not found"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> takeMoney(sentCurrency, amount, card));
        executorService.submit(() -> addMoney(sentAndReceivedCurrencies, amount, card));
        executorService.shutdown();

        try {
            System.out.println(executorService.awaitTermination(1, TimeUnit.MINUTES));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void takeMoney(String sentCurrency, Double amount, Card card) {
        switch (sentCurrency) {
            case "KZT" -> card.setBalanceKzt(card.getBalanceKzt() - amount);
            case "USD" -> card.setBalanceUsd(card.getBalanceUsd() - amount);
            case "EUR" -> card.setBalanceEur(card.getBalanceEur() - amount);
        }
    }

    private void addMoney(String sentAndReceivedCurrencies, Double amount, Card card) {
        switch (sentAndReceivedCurrencies) {
            case "USD-KZT" -> card.setBalanceKzt(card.getBalanceKzt() + (amount * ExchangeRates.USD_TO_KZT.getValue()));
            case "EUR-KZT" -> card.setBalanceKzt(card.getBalanceKzt() + (amount * ExchangeRates.EUR_TO_KZT.getValue()));
            case "KZT-USD" -> card.setBalanceUsd(card.getBalanceUsd() + (amount * ExchangeRates.KZT_TO_USD.getValue()));
            case "EUR-USD" -> card.setBalanceUsd(card.getBalanceUsd() + (amount * ExchangeRates.EUR_TO_USD.getValue()));
            case "USD-EUR" -> card.setBalanceEur(card.getBalanceEur() + (amount * ExchangeRates.USD_TO_EUR.getValue()));
            case "KZT-EUR" -> card.setBalanceEur(card.getBalanceEur() + (amount * ExchangeRates.KZT_TO_EUR.getValue()));
        }
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card save(Card entity) {
        return cardRepository.save(entity);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @Override
    public void delete(Card entity) {
        cardRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return cardRepository.count();
    }
}
