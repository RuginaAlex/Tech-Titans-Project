package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.StockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockOptionsRepository extends JpaRepository<StockOptions, Integer> {
    Optional<StockOptions> findByTicker(String ticker);
}
