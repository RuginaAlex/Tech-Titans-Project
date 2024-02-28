package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.BankAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccounts, Integer> {
    Optional<BankAccounts> findByAccountNumber(String accountNumber);


}

