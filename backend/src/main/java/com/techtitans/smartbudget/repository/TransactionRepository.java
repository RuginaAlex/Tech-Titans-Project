package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.AccountInventory;
import com.techtitans.smartbudget.model.BankAccounts;
import com.techtitans.smartbudget.model.Transaction;
import com.techtitans.smartbudget.service.BankAccountsService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @NotNull
    @Query("SELECT t FROM Transaction t join BankAccounts b on b.account_id = t.account.account_id where b.user.user_id = :id AND t.stockOptions.ticker = :ticker AND t.type = :type")
    List<Transaction> findByUserIdAndTickerAndType(int id, String ticker, String type);
}
