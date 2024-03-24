package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.StockOptions;
import com.techtitans.smartbudget.repository.StockOptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockOptionsService {
    private final StockOptionsRepository stockOptionsRepository;

    public StockOptions create(StockOptions company) {
        return stockOptionsRepository.save(company);
    }

    public List<StockOptions> getAll() {
        return stockOptionsRepository.findAll();
    }

    public Optional<StockOptions> getById(int companyId) {
        return stockOptionsRepository.findById(companyId);
    }

    public void update(int companyId, StockOptions updatedCompany) {
        Optional<StockOptions> existingCompanyOptional = stockOptionsRepository.findById(companyId);
        if (existingCompanyOptional.isPresent()) {
            StockOptions existingCompany = existingCompanyOptional.get();

            // Update fields here
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setTicker(updatedCompany.getTicker());
            existingCompany.setGrade(updatedCompany.getGrade());
            existingCompany.setLast_date_fetched(updatedCompany.getLast_date_fetched());
            stockOptionsRepository.save(existingCompany);
        } else {
            throw new RuntimeException("Company not found with id: " + companyId);
        }
    }

    public void delete(int companyId) {
        stockOptionsRepository.deleteById(companyId);
        //TODO Handle company not found case
    }

    public Optional<StockOptions> getByTicker(String ticker) {
        return stockOptionsRepository.findByTicker(ticker);
    }
}
