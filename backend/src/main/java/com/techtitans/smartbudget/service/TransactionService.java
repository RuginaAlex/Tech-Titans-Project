package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.model.Transaction;

import com.techtitans.smartbudget.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getById(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void update(int transactionId, Transaction updatedTransaction) {
        Optional<Transaction> existingTransactionOptional = transactionRepository.findById(transactionId);
        if (existingTransactionOptional.isPresent()) {
            Transaction existingTransaction = existingTransactionOptional.get();

            existingTransaction.setAccount(updatedTransaction.getAccount());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setType(updatedTransaction.getType());
            existingTransaction.setTimestamp(updatedTransaction.getTimestamp());
            existingTransaction.setDescription(updatedTransaction.getDescription());

            transactionRepository.save(existingTransaction);
        } else {
            throw new RuntimeException("Transaction not found with id: " + transactionId);
        }
    }

    public void delete(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

}
