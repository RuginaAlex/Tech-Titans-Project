package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.AccountInventory;
import com.techtitans.smartbudget.model.BankAccounts;
import com.techtitans.smartbudget.model.Transaction;

import com.techtitans.smartbudget.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    private BankAccountsService bankAccountsService;

    @Autowired
    private AccountInventoryService accountInventoryService;

    public Optional<Transaction> add(Transaction transaction) {
        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Retrieve the associated BankAccount and AccountInventory
        var bankAccount = bankAccountsService.getById(transaction.getAccount().getUser().getUser_id()).get();
        AccountInventory accountInventory = accountInventoryService.getByUserIdAndAccountId(transaction.getStockOptions().getId_company(), transaction.getAccount().getAccount_id()).get();

        // Update the BankAccount and AccountInventory based on the transaction details
        if (Objects.equals(transaction.getType(), "BUYING")) {
            bankAccount.setBalance(bankAccount.getBalance() - transaction.getAmount() * transaction.getPrice());
            accountInventory.setNrOfStocksOwned(accountInventory.getNrOfStocksOwned() + transaction.getAmount());
        } else if (Objects.equals(transaction.getType(), "SELLING")) {
            bankAccount.setBalance(bankAccount.getBalance() + transaction.getAmount() * transaction.getPrice());
            accountInventory.setNrOfStocksOwned(accountInventory.getNrOfStocksOwned() - transaction.getAmount());
        }

        bankAccountsService.update(bankAccount.getUser().getUser_id(), bankAccount);
        accountInventoryService.update(accountInventory.getAccount_inventory_id(), accountInventory);

        return Optional.of(savedTransaction);
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

    public List<Transaction> getByUserIdAndTickerAndType(int id, String ticker, String type) {
        return transactionRepository.findByUserIdAndTickerAndType(id, ticker, type);
    }
}
