package com.techtitans.smartbudget.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "saving_goals",schema = "public")
public class SavingGoals {
    @Id
    @Column(name = "goal_id")
    private int goal_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "User cannot be null")
    private Users user;

    @Column(name = "target_amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "Target amount must be greater than 0")
    private double target_amount;

    @Column(name = "current_amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "Target amount must be greater than 0")
    private double current_amount;

    @Column(name = "deadline", nullable = false)
    @Temporal(TemporalType.DATE)
    @FutureOrPresent(message = "Deadline must be in the future or present")
    private Date deadline;



}
