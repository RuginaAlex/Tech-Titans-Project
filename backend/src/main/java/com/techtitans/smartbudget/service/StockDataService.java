package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.StockData;
import com.techtitans.smartbudget.repository.StockDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockDataService {
    private final StockDataRepository stockDataRepository;

    public StockData create(StockData stockData) {
        return stockDataRepository.save(stockData);
    }

    public List<StockData> getAll() {
        return stockDataRepository.findAll();
    }

    public Optional<StockData> getById(int companyDataId) {
        return stockDataRepository.findById(companyDataId);
    }

    public void update(int companyDataId, StockData updatedStockData) {
        Optional<StockData> existingCompanyDataOptional = stockDataRepository.findById(companyDataId);
        if (existingCompanyDataOptional.isPresent()) {
            StockData existingStockData = existingCompanyDataOptional.get();

            // Update fields here
            existingStockData.setDate(updatedStockData.getDate());
            existingStockData.setOpen(updatedStockData.getOpen());
            existingStockData.setHigh(updatedStockData.getHigh());
            existingStockData.setLow(updatedStockData.getLow());
            existingStockData.setClose(updatedStockData.getClose());

            stockDataRepository.save(existingStockData);
        } else {
            throw new RuntimeException("Company dataManagement not found with id: " + companyDataId);
        }
    }

    public void delete(int companyDataId) {
        stockDataRepository.deleteById(companyDataId);
        //TODO Handle company dataManagement not found case
    }

    public List<StockData> getByTicker(String ticker) {
        return stockDataRepository.findByTicker(ticker);
    }
}
