package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.exceptions.InsufficientFundsException;
import com.techtitans.smartbudget.model.BankAccounts;
import com.techtitans.smartbudget.model.Transaction;
import com.techtitans.smartbudget.repository.BankAccountsRepository;
import com.techtitans.smartbudget.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountsService {
    private final BankAccountsRepository bankAccountsRepository;
    private final TransactionRepository transactionRepository;

    public BankAccounts create(BankAccounts bankAccount) {
        return bankAccountsRepository.save(bankAccount);
    }

    public List<BankAccounts> getAll() {
        return bankAccountsRepository.findAll();
    }

    public Optional<BankAccounts> getById(int accountId) {
        return bankAccountsRepository.findById(accountId);
    }

    public void update(int accountId, BankAccounts updatedBankAccount) {
        Optional<BankAccounts> existingAccountOptional = bankAccountsRepository.findById(accountId);
        if (existingAccountOptional.isPresent()) {
            BankAccounts existingAccount = existingAccountOptional.get();

            existingAccount.setAccountNumber(updatedBankAccount.getAccountNumber());
            existingAccount.setCurrency(updatedBankAccount.getCurrency());
            existingAccount.setBalance(updatedBankAccount.getBalance());
            existingAccount.setAccount_Type(updatedBankAccount.getAccount_Type());

            bankAccountsRepository.save(existingAccount); // Salvăm contul bancar actualizat
        } else {
            throw new RuntimeException("Bank account not found with id: " + accountId);
        }
    }

    public void delete(int accountId) {
        bankAccountsRepository.deleteById(accountId);
    }

    @Transactional
    public void transferFunds(String senderIban, String recipientIban, double amount) {
        // Găsește conturile bancare după IBAN
        BankAccounts senderAccount = bankAccountsRepository.findByAccountNumber(senderIban)
                .orElseThrow(() -> new EntityNotFoundException("Contul expeditorului nu a fost găsit."));
        BankAccounts recipientAccount = bankAccountsRepository.findByAccountNumber(recipientIban)
                .orElseThrow(() -> new EntityNotFoundException("Contul destinatarului nu a fost găsit."));

        // Verifică dacă expeditorul are suficienți bani
        if (senderAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Sold insuficient pentru transfer.");
        }

        // Procesează retragerea din contul expeditorului
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        Transaction withdrawalTransaction = new Transaction();
        withdrawalTransaction.setAccount(senderAccount);
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setType("WITHDRAWAL");
        withdrawalTransaction.setTimestamp(LocalDateTime.now());
        withdrawalTransaction.setDescription("Transfer to " + recipientIban);
        transactionRepository.save(withdrawalTransaction);

        // Procesează depunerea în contul destinatarului
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        Transaction depositTransaction = new Transaction();
        depositTransaction.setAccount(recipientAccount);
        depositTransaction.setAmount(amount);
        depositTransaction.setType("DEPOSIT");
        depositTransaction.setTimestamp(LocalDateTime.now());
        depositTransaction.setDescription("Transfer from " + senderIban);
        transactionRepository.save(depositTransaction);

        // Salvează ambele conturi cu noile solduri
        bankAccountsRepository.save(senderAccount);
        bankAccountsRepository.save(recipientAccount);
    }

}
