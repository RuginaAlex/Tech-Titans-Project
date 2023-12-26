package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.InvestmentRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRecommendationsRepository extends JpaRepository<InvestmentRecommendations, String> {

}
