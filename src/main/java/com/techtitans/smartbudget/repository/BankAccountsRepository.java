package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.BankAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccounts, String> {
}
