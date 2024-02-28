package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.CompanyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDataRepository extends JpaRepository<CompanyData, Integer> {
}
