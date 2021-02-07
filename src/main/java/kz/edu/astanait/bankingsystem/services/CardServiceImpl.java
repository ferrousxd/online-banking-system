package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.exceptions.CardNotFoundException;
import kz.edu.astanait.bankingsystem.exceptions.UserNotFoundException;
import kz.edu.astanait.bankingsystem.models.Card;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.CardRepository;
import kz.edu.astanait.bankingsystem.repositories.UserRepository;
import kz.edu.astanait.bankingsystem.services.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List<Card> getCardsByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
        return cardRepository.findCardsByUser(user);
    }

    @Transactional
    public void makeTransaction(Long senderId, Long receiverId, Double amount) {
        System.out.println(senderId);
        Card sender = cardRepository.findById(senderId).orElseThrow(() -> new CardNotFoundException("Sender's card not found"));
        sender.setBalanceKzt(sender.getBalanceKzt() - amount);

        if (receiverId != null) {
            System.out.println(receiverId);
            Card receiver = cardRepository.findById(receiverId).orElseThrow(() -> new CardNotFoundException("Receiver's card not found"));
            receiver.setBalanceKzt(receiver.getBalanceKzt() + amount);
        }
    }

    @Transactional
    public void convert(Long cardId, String sentCurrency, String receivedCurrency, Double amount) {
        if (sentCurrency.equals(receivedCurrency))
            return;

        String sentAndReceivedCurrencies = sentCurrency + "-" + receivedCurrency;

        takeMoney(sentCurrency, amount, cardId);
        addMoney(sentAndReceivedCurrencies, amount, cardId);
    }

    private void takeMoney(String sentCurrency, Double amount, Long cardId) {
        switch (sentCurrency) {
            case "KZT" -> cardRepository.takeMoneyFromBalanceKzt(amount, cardId);
            case "USD" -> cardRepository.takeMoneyFromBalanceUsd(amount, cardId);
            case "EUR" -> cardRepository.takeMoneyFromBalanceEur(amount, cardId);
        }
    }

    private void addMoney(String sentAndReceivedCurrencies, Double amount, Long cardId) {
        switch (sentAndReceivedCurrencies) {
            case "USD-KZT" -> cardRepository.addMoneyToBalanceKztFromBalanceUsd(amount, cardId);
            case "EUR-KZT" -> cardRepository.addMoneyToBalanceKztFromBalanceEur(amount, cardId);
            case "KZT-USD" -> cardRepository.addMoneyToBalanceUsdFromBalanceKzt(amount, cardId);
            case "EUR-USD" -> cardRepository.addMoneyToBalanceUsdFromBalanceEur(amount, cardId);
            case "USD-EUR" -> cardRepository.addMoneyToBalanceEurFromBalanceUsd(amount, cardId);
            case "KZT-EUR" -> cardRepository.addMoneyToBalanceEurFromBalanceKzt(amount, cardId);
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
