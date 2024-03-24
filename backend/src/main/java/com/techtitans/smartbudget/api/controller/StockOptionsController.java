package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.service.StockOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class StockOptionsController {

    @Autowired
    private StockOptionsService stockOptionsService;

    @PostMapping("/stockoption")
    public ResponseEntity<com.techtitans.smartbudget.model.StockOptions> createCompany(@RequestBody com.techtitans.smartbudget.model.StockOptions company) {
        com.techtitans.smartbudget.model.StockOptions createdCompany = stockOptionsService.create(company);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping("/stockoptions")
    public ResponseEntity<List<com.techtitans.smartbudget.model.StockOptions>> getAllCompanies() {
        List<com.techtitans.smartbudget.model.StockOptions> companies = stockOptionsService.getAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/stockoption/{companyId}")
    public ResponseEntity<com.techtitans.smartbudget.model.StockOptions> getCompanyById(@PathVariable int companyId) {
        return stockOptionsService.getById(companyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stockoption/ticker/{ticker}")
    public ResponseEntity<com.techtitans.smartbudget.model.StockOptions> getCompanyByTicker(@PathVariable String ticker) {
        return stockOptionsService.getByTicker(ticker)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/stockoption/{companyId}")
    public ResponseEntity<Void> updateCompany(@PathVariable int companyId,@RequestBody com.techtitans.smartbudget.model.StockOptions company) {
        stockOptionsService.update(companyId, company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stockoption/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int companyId) {
        stockOptionsService.delete(companyId);
        return ResponseEntity.ok().build();
    }

}
