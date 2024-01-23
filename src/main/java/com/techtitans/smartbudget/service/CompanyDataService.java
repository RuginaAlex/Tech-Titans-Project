package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.model.CompanyData;
import com.techtitans.smartbudget.repository.CompanyDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyDataService {
    private final CompanyDataRepository companyDataRepository;

    public CompanyData create(CompanyData companyData) {
        return companyDataRepository.save(companyData);
    }

    public List<CompanyData> getAll() {
        return companyDataRepository.findAll();
    }

    public Optional<CompanyData> getById(int companyDataId) {
        return companyDataRepository.findById(companyDataId);
    }

    public void update(int companyDataId, CompanyData updatedCompanyData) {
        Optional<CompanyData> existingCompanyDataOptional = companyDataRepository.findById(companyDataId);
        if (existingCompanyDataOptional.isPresent()) {
            CompanyData existingCompanyData = existingCompanyDataOptional.get();

            // Update fields here
            existingCompanyData.setDate(updatedCompanyData.getDate());
            existingCompanyData.setOpen(updatedCompanyData.getOpen());
            existingCompanyData.setHigh(updatedCompanyData.getHigh());
            existingCompanyData.setLow(updatedCompanyData.getLow());
            existingCompanyData.setClose(updatedCompanyData.getClose());

            companyDataRepository.save(existingCompanyData);
        } else {
            throw new RuntimeException("Company data not found with id: " + companyDataId);
        }
    }

    public void delete(int companyDataId) {
        companyDataRepository.deleteById(companyDataId);
        //TODO Handle company data not found case
    }

}
