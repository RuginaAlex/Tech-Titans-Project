package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.AccountInventory;
import com.techtitans.smartbudget.service.AccountInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountInventoryController {

    @Autowired
    private AccountInventoryService accountInventoryService;


    @PostMapping("/accountInventory")
    public ResponseEntity<AccountInventory> createAccountInventory(@RequestBody AccountInventory accountInventory) {
        AccountInventory createdAccountInventory = accountInventoryService.create(accountInventory);
        return ResponseEntity.ok(createdAccountInventory);
    }

    @GetMapping("/accountInventory/{userId}/{accountId}")
    public ResponseEntity<AccountInventory> getAccountInventoryByUserIdAndAccountId(@PathVariable int userId, @PathVariable int accountId) {
        return accountInventoryService.getByUserIdAndAccountId(userId, accountId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/accountInventory/{accountInventoryId}")
    public ResponseEntity<AccountInventory> getAccountInventoryById(@PathVariable int accountInventoryId) {
        return accountInventoryService.getById(accountInventoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/accountInventory/{accountInventoryId}")
    public ResponseEntity<Void> updateAccountInventory(@PathVariable int accountInventoryId, @RequestBody AccountInventory accountInventory) {
        accountInventoryService.update(accountInventoryId, accountInventory);
        return ResponseEntity.ok().build();
    }

}
