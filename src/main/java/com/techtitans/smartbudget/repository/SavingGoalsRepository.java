package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.SavingGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingGoalsRepository extends JpaRepository<SavingGoals, Integer> {
}
