package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.Companies;
import com.techtitans.smartbudget.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompaniesService companiesService;

    @PostMapping("/company")
    public ResponseEntity<Companies> createCompany(Companies company) {
        Companies createdCompany = companiesService.create(company);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Companies>> getAllCompanies() {
        List<Companies> companies = companiesService.getAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Companies> getCompanyById(int companyId) {
        return companiesService.getById(companyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/company/{companyId}")
    public ResponseEntity<Void> updateCompany(int companyId, Companies company) {
        companiesService.update(companyId, company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/company/{companyId}")
    public ResponseEntity<Void> deleteCompany(int companyId) {
        companiesService.delete(companyId);
        return ResponseEntity.ok().build();
    }

}