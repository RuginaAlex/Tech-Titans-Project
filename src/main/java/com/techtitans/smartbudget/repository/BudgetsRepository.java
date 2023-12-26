package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.Budgets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetsRepository extends JpaRepository<Budgets, String> {
}
