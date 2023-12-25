package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.SavingGoals;
import com.techtitans.smartbudget.service.SavingGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savingGoals")
public class SavingGoalsController {

    @Autowired
    private SavingGoalsService savingGoalsService;

    @PostMapping
    public ResponseEntity<SavingGoals> createSavingGoal(@RequestBody SavingGoals savingGoal) {
        SavingGoals createdSavingGoal = savingGoalsService.create(savingGoal);
        return ResponseEntity.ok(createdSavingGoal);
    }

    @GetMapping
    public ResponseEntity<List<SavingGoals>> getAllSavingGoals() {
        List<SavingGoals> savingGoals = savingGoalsService.getAll();
        return ResponseEntity.ok(savingGoals);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<SavingGoals> getSavingGoalById(@PathVariable String goalId) {
        return savingGoalsService.getById(goalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<Void> updateSavingGoal(@PathVariable String goalId, @RequestBody SavingGoals savingGoal) {
        savingGoalsService.update(goalId, savingGoal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteSavingGoal(@PathVariable String goalId) {
        savingGoalsService.delete(goalId);
        return ResponseEntity.ok().build();
    }

}
