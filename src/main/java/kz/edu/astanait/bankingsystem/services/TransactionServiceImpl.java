package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.exceptions.TransactionNotFoundException;
import kz.edu.astanait.bankingsystem.models.Transaction;
import kz.edu.astanait.bankingsystem.models.User;
import kz.edu.astanait.bankingsystem.repositories.TransactionRepository;
import kz.edu.astanait.bankingsystem.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findTransactionsByCard_User(User user) {
        return transactionRepository.findTransactionsByCard_User(user);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction entity) {
        return transactionRepository.save(entity);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }

    @Override
    public void delete(Transaction entity) {
        transactionRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return transactionRepository.count();
    }
}
