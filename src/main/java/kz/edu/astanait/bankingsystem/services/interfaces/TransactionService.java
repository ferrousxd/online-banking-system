package kz.edu.astanait.bankingsystem.services.interfaces;

import kz.edu.astanait.bankingsystem.models.Transaction;
import kz.edu.astanait.bankingsystem.models.User;

import java.util.List;

public interface TransactionService extends GenericService<Transaction> {

    List<Transaction> findTransactionsByCard_User(User user);
}
