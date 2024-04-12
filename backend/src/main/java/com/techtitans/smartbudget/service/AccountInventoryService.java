package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.AccountInventory;
import com.techtitans.smartbudget.repository.AccountInventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountInventoryService {
    private final AccountInventoryRepository accountInventoryRepository;

    public Optional<AccountInventory> getById(int accountInventoryId) {
        return accountInventoryRepository.findById(accountInventoryId);
    }

    public Optional<AccountInventory> getByUserIdAndAccountId(int companyId, int accountId) {
        return accountInventoryRepository.findByUserIdAndAccountId(companyId, accountId);
    }

    public AccountInventory create(AccountInventory accountInventory) {
        return accountInventoryRepository.save(accountInventory);
    }

    public void update(int accountId, AccountInventory updatedAccountInventory) {
        Optional<AccountInventory> existingAccountInventoryOptional = accountInventoryRepository.findById(accountId);
        if (existingAccountInventoryOptional.isPresent()) {
            AccountInventory existingAccountInventory = existingAccountInventoryOptional.get();

            // Update fields here
            existingAccountInventory.setNrOfStocksOwned(updatedAccountInventory.getNrOfStocksOwned());

            accountInventoryRepository.save(existingAccountInventory);
        } else {
            throw new RuntimeException("AccountInventory not found with id: " + accountId);
        }
    }


}
