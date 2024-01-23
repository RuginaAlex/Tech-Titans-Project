package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.api.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> {
}
