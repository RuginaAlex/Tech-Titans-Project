package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Metode personalizate, daca este necesar
}
