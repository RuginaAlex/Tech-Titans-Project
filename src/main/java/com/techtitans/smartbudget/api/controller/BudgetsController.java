package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.Budgets;
import com.techtitans.smartbudget.service.BudgetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetsController {

    @Autowired
    private BudgetsService budgetsService;

    @PostMapping
    public ResponseEntity<Budgets> createBudget(@RequestBody Budgets budget) {
        Budgets createdBudget = budgetsService.create(budget);
        return ResponseEntity.ok(createdBudget);
    }

    @GetMapping
    public ResponseEntity<List<Budgets>> getAllBudgets() {
        List<Budgets> budgets = budgetsService.getAll();
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budgets> getBudgetById(@PathVariable String budgetId) {
        return budgetsService.getById(budgetId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<Void> updateBudget(@PathVariable String budgetId, @RequestBody Budgets budget) {
        budgetsService.update(budgetId, budget);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable String budgetId) {
        budgetsService.delete(budgetId);
        return ResponseEntity.ok().build();
    }
}
