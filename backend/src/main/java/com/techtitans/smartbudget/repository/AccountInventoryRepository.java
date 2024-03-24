package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.AccountInventory;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountInventoryRepository extends JpaRepository<AccountInventory, Integer> {

    @Query("SELECT ai FROM AccountInventory ai WHERE ai.company.id_company = :companyId and ai.account.account_id = :accountId")
    @NotNull
    Optional<AccountInventory> findByUserIdAndAccountId(Integer companyId, Integer accountId);

}
