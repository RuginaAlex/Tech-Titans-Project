package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.ExpenseCategory;
import com.techtitans.smartbudget.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenseCategories")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @PostMapping
    public ResponseEntity<ExpenseCategory> createExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
        ExpenseCategory createdExpenseCategory = expenseCategoryService.create(expenseCategory);
        return ResponseEntity.ok(createdExpenseCategory);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllExpenseCategories() {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.getAll();
        return ResponseEntity.ok(expenseCategories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ExpenseCategory> getExpenseCategoryById(@PathVariable String categoryId) {
        return expenseCategoryService.getById(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateExpenseCategory(@PathVariable String categoryId, @RequestBody ExpenseCategory expenseCategory) {
        expenseCategoryService.update(categoryId, expenseCategory);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable String categoryId) {
        expenseCategoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }

}
