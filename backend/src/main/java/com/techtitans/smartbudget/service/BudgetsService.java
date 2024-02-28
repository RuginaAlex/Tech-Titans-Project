package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.Budgets;
import com.techtitans.smartbudget.repository.BudgetsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BudgetsService {
    private final BudgetsRepository budgetsRepository;

    public Budgets create(Budgets budget) {
        return budgetsRepository.save(budget);
    }

    public List<Budgets> getAll() {
        return budgetsRepository.findAll();
    }

    public Optional<Budgets> getById(int budgetId){
        return budgetsRepository.findById(budgetId);
    }

    public void update(int budgetId, Budgets updatedBudget) {
        Optional<Budgets> existingBudgetOptional = budgetsRepository.findById(budgetId);
        if (existingBudgetOptional.isPresent()) {
            Budgets existingBudget = existingBudgetOptional.get();

            // Update fields here
            existingBudget.setAmount(updatedBudget.getAmount());
            existingBudget.setCategory(updatedBudget.getCategory());
            existingBudget.setStartDate(updatedBudget.getStartDate());
            existingBudget.setEndDate(updatedBudget.getEndDate());

            // Saving updated budget
            budgetsRepository.save(existingBudget);
        } else {
            // Handling the case where the budget doesn't exist
            throw new RuntimeException("Budget not found with id: " + budgetId);
        }
    }

    public void delete (int budgetId){
        budgetsRepository.deleteById(budgetId);
        //TODO :  if budget was not found
    }
}
