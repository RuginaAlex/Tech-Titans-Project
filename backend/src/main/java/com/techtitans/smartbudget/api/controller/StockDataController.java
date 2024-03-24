package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.StockData;
import com.techtitans.smartbudget.service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class StockDataController {

    @Autowired
    private StockDataService stockDataService;

    @PostMapping("/stockData")
    public ResponseEntity<StockData> createCompanyData(@RequestBody StockData stockData) {
        StockData createdStockData = stockDataService.create(stockData);
        return ResponseEntity.ok(createdStockData);
    }

    @GetMapping("/stocksData")
    public ResponseEntity<List<StockData>> getAllCompanyData() {
        List<StockData> stockData = stockDataService.getAll();
        return ResponseEntity.ok(stockData);
    }

    @GetMapping("/stockData/{companyDataId}")
    public ResponseEntity<StockData> getCompanyDataById(@PathVariable int companyDataId) {
        return stockDataService.getById(companyDataId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stockData/ticker/{ticker}")
    public ResponseEntity<List<StockData>> getCompanyDataByTicker(@PathVariable String ticker) {
        List<StockData> stockData = stockDataService.getByTicker(ticker);
        return ResponseEntity.ok(stockData);
    }

    @PutMapping("/stockData/{companyDataId}")
    public ResponseEntity<Void> updateCompanyData(@PathVariable int companyDataId,@RequestBody StockData stockData) {
        stockDataService.update(companyDataId, stockData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stockData/{companyDataId}")
    public ResponseEntity<Void> deleteCompanyData(@PathVariable int companyDataId) {
        stockDataService.delete(companyDataId);
        return ResponseEntity.ok().build();
    }

}
