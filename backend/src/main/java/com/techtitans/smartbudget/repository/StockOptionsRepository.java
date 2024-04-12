package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.StockOptions;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockOptionsRepository extends JpaRepository<StockOptions, Integer> {
    @NotNull
    @Query("SELECT s FROM StockOptions s WHERE s.ticker = :ticker")
    Optional<StockOptions> findByTicker(String ticker);
}
