package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.BankAccounts;
import com.techtitans.smartbudget.service.BankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccounts")
public class BankAccountsController {

    @Autowired
    private BankAccountsService bankAccountsService;

    @PostMapping
    public ResponseEntity<BankAccounts> createBankAccount(@RequestBody BankAccounts bankAccount) {
        BankAccounts createdBankAccount = bankAccountsService.create(bankAccount);
        return ResponseEntity.ok(createdBankAccount);
    }

    @GetMapping
    public ResponseEntity<List<BankAccounts>> getAllBankAccounts() {
        List<BankAccounts> bankAccounts = bankAccountsService.getAll();
        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccounts> getBankAccountById(@PathVariable String accountId) {
        return bankAccountsService.getById(accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Void> updateBankAccount(@PathVariable String accountId, @RequestBody BankAccounts bankAccount) {
        bankAccountsService.update(accountId, bankAccount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable String accountId) {
        bankAccountsService.delete(accountId);
        return ResponseEntity.ok().build();
    }

}
