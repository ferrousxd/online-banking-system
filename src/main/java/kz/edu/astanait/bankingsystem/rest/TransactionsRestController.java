package kz.edu.astanait.bankingsystem.rest;

import kz.edu.astanait.bankingsystem.models.Transaction;
import kz.edu.astanait.bankingsystem.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionsRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionsRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.findAll();
    }
}
