package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.model.BankAccounts;
import com.techtitans.smartbudget.repository.BankAccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountsService {
    private final BankAccountsRepository bankAccountsRepository;

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

            // Update fields here
            existingAccount.setAccount_number(updatedBankAccount.getAccount_number());
            existingAccount.setCurrency(updatedBankAccount.getCurrency());
            existingAccount.setBalance(updatedBankAccount.getBalance());
            existingAccount.setAccount_Type(updatedBankAccount.getAccount_Type());
            // ... și așa mai departe pentru fiecare câmp pe care dorești să-l actualizezi

            bankAccountsRepository.save(existingAccount); // Salvăm contul bancar actualizat
        } else {
            throw new RuntimeException("Bank account not found with id: " + accountId); // sau orice altă gestionare a erorilor preferi
        }
    }

    public void delete(int accountId) {
        bankAccountsRepository.deleteById(accountId);
        // Handle account not found case
    }
}
