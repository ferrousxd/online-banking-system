package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardsByUser(User user);

    @Modifying
    @Query("update Card c set c.balanceKzt = c.balanceKzt - :amount where c.id = :cardId")
    void takeMoneyFromBalanceKzt(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceUsd = c.balanceUsd - :amount where c.id = :cardId")
    void takeMoneyFromBalanceUsd(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceEur = c.balanceEur - :amount where c.id = :cardId")
    void takeMoneyFromBalanceEur(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceKzt = c.balanceKzt + (420.9 * :amount) where c.id = :cardId")
    void addMoneyToBalanceKztFromBalanceUsd(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceKzt = c.balanceKzt + (503.0 * :amount) where c.id = :cardId")
    void addMoneyToBalanceKztFromBalanceEur(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceUsd = c.balanceUsd + (0.0024 * :amount) where c.id = :cardId")
    void addMoneyToBalanceUsdFromBalanceKzt(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceUsd = c.balanceUsd + (1.2 * :amount) where c.id = :cardId")
    void addMoneyToBalanceUsdFromBalanceEur(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceEur = c.balanceEur + (0.83 * :amount) where c.id = :cardId")
    void addMoneyToBalanceEurFromBalanceUsd(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);

    @Modifying
    @Query("update Card c set c.balanceEur = c.balanceEur + (0.002 * :amount) where c.id = :cardId")
    void addMoneyToBalanceEurFromBalanceKzt(@Param(value = "amount") Double amount, @Param(value = "cardId") Long cardId);
}
