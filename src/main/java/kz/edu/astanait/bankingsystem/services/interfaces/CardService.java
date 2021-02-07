package kz.edu.astanait.bankingsystem.services.interfaces;

import kz.edu.astanait.bankingsystem.models.Card;

import java.util.List;

public interface CardService extends GenericService<Card> {
    List<Card> getCardsByPhoneNumber(String phoneNumber);
    void makeTransaction(Long cardId, Long receiverId, Double remainingMoney);
    void convert(Long cardId, String sentCurrency, String receivedCurrency, Double amount);
}
