package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.InvestmentRecommendations;
import com.techtitans.smartbudget.repository.InvestmentRecommendationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvestmentRecommendationsService {

    private final InvestmentRecommendationsRepository investmentRecommendationsRepository;

    public InvestmentRecommendations create(InvestmentRecommendations investmentRecommendation) {
        return investmentRecommendationsRepository.save(investmentRecommendation);
    }

    public List<InvestmentRecommendations> getAll() {
        return investmentRecommendationsRepository.findAll();
    }

    public Optional<InvestmentRecommendations> getById(int recommendationId) {
        return investmentRecommendationsRepository.findById(recommendationId);
    }

    public void update(int recommendationId, InvestmentRecommendations updatedRecommendation) {
        Optional<InvestmentRecommendations> existingRecommendationOptional = investmentRecommendationsRepository.findById(recommendationId);
        if (existingRecommendationOptional.isPresent()) {
            InvestmentRecommendations existingRecommendation = existingRecommendationOptional.get();

            existingRecommendation.setUser(updatedRecommendation.getUser());
            existingRecommendation.setDescription(updatedRecommendation.getDescription());
            existingRecommendation.setRiskLevel(updatedRecommendation.getRiskLevel());
            existingRecommendation.setPotentialReturn(updatedRecommendation.getPotentialReturn());

            investmentRecommendationsRepository.save(existingRecommendation);
        } else {
            throw new RuntimeException("Investment Recommendation not found with id: " + recommendationId);
        }
    }

    public void delete(int recommendationId) {
        investmentRecommendationsRepository.deleteById(recommendationId);
    }

}
