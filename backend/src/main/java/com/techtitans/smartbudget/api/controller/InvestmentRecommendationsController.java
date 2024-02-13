package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.InvestmentRecommendations;
import com.techtitans.smartbudget.service.InvestmentRecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvestmentRecommendationsController {

    @Autowired
    private InvestmentRecommendationsService investmentRecommendationsService;

    @PostMapping("/investmentRecommendation")
    public ResponseEntity<InvestmentRecommendations> createInvestmentRecommendation(@RequestBody InvestmentRecommendations investmentRecommendation) {
        InvestmentRecommendations createdInvestmentRecommendation = investmentRecommendationsService.create(investmentRecommendation);
        return ResponseEntity.ok(createdInvestmentRecommendation);
    }

    @GetMapping("/investmentRecommendations")
    public ResponseEntity<List<InvestmentRecommendations>> getAllInvestmentRecommendations() {
        List<InvestmentRecommendations> investmentRecommendations = investmentRecommendationsService.getAll();
        return ResponseEntity.ok(investmentRecommendations);
    }

    @GetMapping("/investmentRecommendation/{recommendationId}")
    public ResponseEntity<InvestmentRecommendations> getInvestmentRecommendationById(@PathVariable int recommendationId) {
        return investmentRecommendationsService.getById(recommendationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/investmentRecommendation/{recommendationId}")
    public ResponseEntity<Void> updateInvestmentRecommendation(@PathVariable int recommendationId, @RequestBody InvestmentRecommendations investmentRecommendation) {
        investmentRecommendationsService.update(recommendationId, investmentRecommendation);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/investmentRecommendation/{recommendationId}")
    public ResponseEntity<Void> deleteInvestmentRecommendation(@PathVariable int recommendationId) {
        investmentRecommendationsService.delete(recommendationId);
        return ResponseEntity.ok().build();
    }

}
