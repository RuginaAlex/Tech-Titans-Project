package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.dto.OrderDTO;
import com.techtitans.smartbudget.model.StockOptions;
import com.techtitans.smartbudget.model.Transaction;
import com.techtitans.smartbudget.service.BankAccountsService;
import com.techtitans.smartbudget.service.StockOptionsService;
import com.techtitans.smartbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountsService bankAccountsService;

    @Autowired
    private StockOptionsService stockOptionsService;

    @PostMapping("/transaction/new")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Map<String, Object> requestData) {
        String ticker = (String) requestData.get("ticker");
        int userID = (int) requestData.get("userID");
        double amountOfMoney = (double) requestData.get("amountOfMoney");
        double shareValue = (double) requestData.get("shareValue");
        String type = (String) requestData.get("type");

        var transaction = new Transaction(ThreadLocalRandom.current().nextInt(3, 10000000), bankAccountsService.getById(userID).get(), amountOfMoney/shareValue, type, LocalDateTime.now(), "description", stockOptionsService.getByTicker(ticker).get(), shareValue);
        var createdTransaction = transactionService.add(transaction);
        return createdTransaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
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

    @GetMapping("/transaction/orders/{id}/{ticker}/{type}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserIdAndTickerAndType(@PathVariable int id, @PathVariable String ticker, @PathVariable String type) {
        List<Transaction> transactions = transactionService.getByUserIdAndTickerAndType(id, ticker, type);
        List<OrderDTO> orderDTOs = transactions.stream()
                .map(transaction -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setAmountInvested(transaction.getAmount() * transaction.getPrice());
                    orderDTO.setNumberOfShares(transaction.getAmount());
                    orderDTO.setTimestamp(transaction.getTimestamp());
                    return orderDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping("/transaction/sell")
    public ResponseEntity<Transaction> sellTransaction(@RequestBody Map<String, Object> requestData) {
        String ticker = (String) requestData.get("ticker");
        int userID = (int) requestData.get("userID");
        double amountOfMoney = (double) requestData.get("amountOfMoney");
        double shareValue = (double) requestData.get("shareValue");
        String type = (String) requestData.get("type");

        var transaction = new Transaction(ThreadLocalRandom.current().nextInt(3, 10000000), bankAccountsService.getById(userID).get(), amountOfMoney/shareValue, type, LocalDateTime.now(), "description", stockOptionsService.getByTicker(ticker).get(), shareValue);
        var createdTransaction = transactionService.add(transaction);
        return createdTransaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
