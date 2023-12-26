package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.model.ExpenseCategory;
import com.techtitans.smartbudget.repository.ExpenseCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory create(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    public List<ExpenseCategory> getAll() {
        return expenseCategoryRepository.findAll();
    }

    public Optional<ExpenseCategory> getById(String categoryId) {
        return expenseCategoryRepository.findById(categoryId);
    }

    public void update(String categoryId, ExpenseCategory updatedCategory) {
        Optional<ExpenseCategory> existingCategoryOptional = expenseCategoryRepository.findById(categoryId);
        if (existingCategoryOptional.isPresent()) {
            ExpenseCategory existingCategory = existingCategoryOptional.get();

            existingCategory.setName(updatedCategory.getName());

            expenseCategoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Expense Category not found with id: " + categoryId);
        }
    }


    public void delete(String categoryId) {
        expenseCategoryRepository.deleteById(categoryId);

    }

}