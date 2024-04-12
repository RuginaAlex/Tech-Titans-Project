package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.dto.TransferRequestDTO;
import com.techtitans.smartbudget.api.exceptions.InsufficientFundsException;
import com.techtitans.smartbudget.model.BankAccounts;
import com.techtitans.smartbudget.model.StockOptions;
import com.techtitans.smartbudget.service.BankAccountsService;
import com.techtitans.smartbudget.service.StockOptionsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BankAccountsController {

    @Autowired
    private BankAccountsService bankAccountsService;

    @Autowired
    private StockOptionsService stockOptionsService;

    @PostMapping("/bankAccount")
    public ResponseEntity<BankAccounts> createBankAccount(@RequestBody BankAccounts bankAccount) {
        BankAccounts createdBankAccount = bankAccountsService.create(bankAccount);
        return ResponseEntity.ok(createdBankAccount);
    }

    @GetMapping("/bankAccounts")
    public ResponseEntity<List<BankAccounts>> getAllBankAccounts() {
        List<BankAccounts> bankAccounts = bankAccountsService.getAll();
        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/bankAccount/{userId}")
    public ResponseEntity<BankAccounts> getBankAccountById(@PathVariable int userId) {
        return bankAccountsService.getById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/bankAccount/{userId}")
    public ResponseEntity<Void> updateBankAccount(@PathVariable int accountId, @RequestBody BankAccounts bankAccount) {
        bankAccountsService.update(accountId, bankAccount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/bankAccount/{userId}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable int accountId) {
        bankAccountsService.delete(accountId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferRequestDTO transferRequest) {
        try {
            bankAccountsService.transferFunds(
                    transferRequest.getSenderIban(),
                    transferRequest.getRecipientIban(),
                    transferRequest.getAmount()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException | InsufficientFundsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bankAccount/shares/{userId}/{ticker}")
    public ResponseEntity<Map<String, Double>> getSharesByUserIdAndCompanyId(@PathVariable int userId, @PathVariable String ticker) {
        var companyId = stockOptionsService.getByTicker(ticker).get().getId_company();
        return bankAccountsService.getNumberOfStocksOwnedByUserForCompany(userId, companyId)
                .map(shares -> {
                    Map<String, Double> response = new HashMap<>();
                    response.put("shares", shares);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
