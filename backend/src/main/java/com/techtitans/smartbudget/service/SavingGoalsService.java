package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.SavingGoals;

import com.techtitans.smartbudget.repository.SavingGoalsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SavingGoalsService {

    private final SavingGoalsRepository savingGoalsRepository;

    public SavingGoals create(SavingGoals savingGoal) {
        return savingGoalsRepository.save(savingGoal);
    }

    public List<SavingGoals> getAll() {
        return savingGoalsRepository.findAll();
    }

    public Optional<SavingGoals> getById(int goalId) {
        return savingGoalsRepository.findById(goalId);
    }

    public void update(int goalId, SavingGoals updatedGoal) {
        Optional<SavingGoals> existingGoalOptional = savingGoalsRepository.findById(goalId);
        if (existingGoalOptional.isPresent()) {
            SavingGoals existingGoal = existingGoalOptional.get();

            existingGoal.setUser(updatedGoal.getUser());
            existingGoal.setTarget_amount(updatedGoal.getTarget_amount());
            existingGoal.setCurrent_amount(updatedGoal.getCurrent_amount());
            existingGoal.setDeadline(updatedGoal.getDeadline());

            savingGoalsRepository.save(existingGoal);
        } else {
            throw new RuntimeException("Saving Goal not found with id: " + goalId);
        }
    }

    public void delete(int goalId) {
        savingGoalsRepository.deleteById(goalId);
    }

}
