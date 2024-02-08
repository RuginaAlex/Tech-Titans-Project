package com.techtitans.smartbudget.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "investment_recommendations",schema = "public")
public class InvestmentRecommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private int reccomendation_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "User cannot be null")
    private Users user;

    @Column(name = "description", nullable = false)
    @Size(max = 256, message = "Description must not exceed 256 characters")
    private String description;

    @Column(name = "risk_level", nullable = false)
    @Size(max = 256, message = "Risk level must not exceed 256 characters")
    private String riskLevel;

    @Column(name = "potential_return", nullable = false)
    private double potentialReturn;

}
