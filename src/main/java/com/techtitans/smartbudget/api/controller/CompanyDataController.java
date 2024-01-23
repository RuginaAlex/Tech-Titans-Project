package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.CompanyData;
import com.techtitans.smartbudget.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class CompanyDataController {

    @Autowired
    private CompanyDataService companyDataService;

    @PostMapping("/companyData")
    public ResponseEntity<CompanyData> createCompanyData(CompanyData companyData) {
        CompanyData createdCompanyData = companyDataService.create(companyData);
        return ResponseEntity.ok(createdCompanyData);
    }

    @GetMapping("/companyData")
    public ResponseEntity<List<CompanyData>> getAllCompanyData() {
        List<CompanyData> companyData = companyDataService.getAll();
        return ResponseEntity.ok(companyData);
    }

    @GetMapping("/companyData/{companyDataId}")
    public ResponseEntity<CompanyData> getCompanyDataById(int companyDataId) {
        return companyDataService.getById(companyDataId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/companyData/{companyDataId}")
    public ResponseEntity<Void> updateCompanyData(int companyDataId, CompanyData companyData) {
        companyDataService.update(companyDataId, companyData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/companyData/{companyDataId}")
    public ResponseEntity<Void> deleteCompanyData(int companyDataId) {
        companyDataService.delete(companyDataId);
        return ResponseEntity.ok().build();
    }

}
