package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.InvestmentRecommendations;
import com.techtitans.smartbudget.service.InvestmentRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investmentRecommendations")
public class InvestmentRecommendationsController {

    @Autowired
    private InvestmentRecommendationsService investmentRecommendationsService;

    @PostMapping
    public ResponseEntity<InvestmentRecommendations> createInvestmentRecommendation(@RequestBody InvestmentRecommendations investmentRecommendation) {
        InvestmentRecommendations createdInvestmentRecommendation = investmentRecommendationsService.create(investmentRecommendation);
        return ResponseEntity.ok(createdInvestmentRecommendation);
    }

    @GetMapping
    public ResponseEntity<List<InvestmentRecommendations>> getAllInvestmentRecommendations() {
        List<InvestmentRecommendations> investmentRecommendations = investmentRecommendationsService.getAll();
        return ResponseEntity.ok(investmentRecommendations);
    }

    @GetMapping("/{recommendationId}")
    public ResponseEntity<InvestmentRecommendations> getInvestmentRecommendationById(@PathVariable String recommendationId) {
        return investmentRecommendationsService.getById(recommendationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{recommendationId}")
    public ResponseEntity<Void> updateInvestmentRecommendation(@PathVariable String recommendationId, @RequestBody InvestmentRecommendations investmentRecommendation) {
        investmentRecommendationsService.update(recommendationId, investmentRecommendation);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{recommendationId}")
    public ResponseEntity<Void> deleteInvestmentRecommendation(@PathVariable String recommendationId) {
        investmentRecommendationsService.delete(recommendationId);
        return ResponseEntity.ok().build();
    }

}
