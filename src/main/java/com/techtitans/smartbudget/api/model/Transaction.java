package com.techtitans.smartbudget.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions", schema = "public")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    private int transaction_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private BankAccounts account;

    @Column(name = "amount")
    @Positive(message = "Amount must be a positive number")
    private double amount;


    @Column(name = "type")
    @Pattern(regexp = "DEPOSIT|WITHDRAWAL|TRANSFER", message = "Type must be: DEPOSIT, WITHDRAWAL, TRANSFER")
    private String type;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "Timestamp must be in the past or present")
    private LocalDateTime timestamp;


    @Column(name = "description")
    @NotBlank(message = "Description must not be blank")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;





}
