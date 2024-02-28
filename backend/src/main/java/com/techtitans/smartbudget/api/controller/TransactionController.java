package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.Transaction;
import com.techtitans.smartbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.create(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int transactionId) {
        return transactionService.getById(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/transaction/{transactionId}")
    public ResponseEntity<Void> updateTransaction(@PathVariable int transactionId, @RequestBody Transaction transaction) {
        transactionService.update(transactionId, transaction);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        transactionService.delete(transactionId);
        return ResponseEntity.ok().build();
    }
}
