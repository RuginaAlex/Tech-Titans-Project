package com.techtitans.smartbudget.repository;

import com.techtitans.smartbudget.model.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDataRepository extends JpaRepository<StockData, Integer> {

    @Query("SELECT cd from StockData cd join cd.company c where c.ticker = :ticker")
    List<StockData> findByTicker(String ticker);
}
