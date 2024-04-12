package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.BankAccounts;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountsRepository extends JpaRepository<BankAccounts, Integer> {
    Optional<BankAccounts> findByAccountNumber(String accountNumber);


    @NotNull
    @Override
    @Query("SELECT b FROM BankAccounts b WHERE b.user.user_id = :userId")
    Optional<BankAccounts> findById(@NotNull Integer userId);

    @NotNull
    @Query("select ai.nrOfStocksOwned from AccountInventory ai join BankAccounts b on ai.account = b where b.user.user_id = :userId and ai.company.id_company = :companyId")
    Optional<Double> getNumberOfStocksOwnedByUserForCompany(@NotNull Integer userId, @NotNull Integer companyId);
}

