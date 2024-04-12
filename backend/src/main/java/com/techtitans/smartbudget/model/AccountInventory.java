package com.techtitans.smartbudget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "account_inventory", schema = "public")
public class AccountInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_inventory_id")
    private int account_inventory_id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id_company")
    @NotNull(message = "Company cannot be null")
    private StockOptions company;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @NotNull(message = "Account cannot be null")
    private BankAccounts account;

    @Column(name = "nr_of_stocks_owned")
    private double nrOfStocksOwned;

}
