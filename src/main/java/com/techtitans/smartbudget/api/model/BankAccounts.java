package com.techtitans.smartbudget.api.model;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum Currency {
    RON, EUR, USD
}

enum AccountType {
    CHECKING,
    SAVINGS,
    TIME_DEPOSIT,
    INVESTMENT,
    RETIREMENT,
    BUSINESS
}

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name= "bank_accounts",schema = "public")
public class BankAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private String account_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "User cannot be null")
    private Users user;

    @Column(name = "account_number", length = 24)
    @Pattern(regexp = "RO\\d{2}[A-Z]{4}\\d{16}", message = "Invalid IBAN format")
    private String account_number;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(nullable = false)
    @Positive(message = "Balance must be a positive number")
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType account_Type;


}
