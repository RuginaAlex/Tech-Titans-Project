package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.Budgets;
import com.techtitans.smartbudget.service.BudgetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetsController {

    @Autowired
    private BudgetsService budgetsService;

    @PostMapping("/budget")
    public ResponseEntity<Budgets> createBudget(@RequestBody Budgets budget) {
        Budgets createdBudget = budgetsService.create(budget);
        return ResponseEntity.ok(createdBudget);
    }

    @GetMapping("/budgets")
    public ResponseEntity<List<Budgets>> getAllBudgets() {
        List<Budgets> budgets = budgetsService.getAll();
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<Budgets> getBudgetById(@PathVariable int budgetId) {
        return budgetsService.getById(budgetId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/budget/{budgetId}")
    public ResponseEntity<Void> updateBudget(@PathVariable int budgetId, @RequestBody Budgets budget) {
        budgetsService.update(budgetId, budget);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/budget/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable int budgetId) {
        budgetsService.delete(budgetId);
        return ResponseEntity.ok().build();
    }
}
