package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.SavingGoals;
import com.techtitans.smartbudget.service.SavingGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SavingGoalsController {

    @Autowired
    private SavingGoalsService savingGoalsService;

    @PostMapping("/savingGoal")
    public ResponseEntity<SavingGoals> createSavingGoal(@RequestBody SavingGoals savingGoal) {
        SavingGoals createdSavingGoal = savingGoalsService.create(savingGoal);
        return ResponseEntity.ok(createdSavingGoal);
    }

    @GetMapping("/savingGoals")
    public ResponseEntity<List<SavingGoals>> getAllSavingGoals() {
        List<SavingGoals> savingGoals = savingGoalsService.getAll();
        return ResponseEntity.ok(savingGoals);
    }

    @GetMapping("/savingGoal/{goalId}")
    public ResponseEntity<SavingGoals> getSavingGoalById(@PathVariable int goalId) {
        return savingGoalsService.getById(goalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/savingGoal/{goalId}")
    public ResponseEntity<Void> updateSavingGoal(@PathVariable int goalId, @RequestBody SavingGoals savingGoal) {
        savingGoalsService.update(goalId, savingGoal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/savingGoal/{goalId}")
    public ResponseEntity<Void> deleteSavingGoal(@PathVariable int goalId) {
        savingGoalsService.delete(goalId);
        return ResponseEntity.ok().build();
    }

}
