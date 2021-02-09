package kz.edu.astanait.bankingsystem.repositories;

import kz.edu.astanait.bankingsystem.models.Transaction;
import kz.edu.astanait.bankingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByCard_User(User user);
}
