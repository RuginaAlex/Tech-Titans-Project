package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.model.Companies;
import com.techtitans.smartbudget.repository.CompaniesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompaniesService {
    private final CompaniesRepository companiesRepository;

    public Companies create(Companies company) {
        return companiesRepository.save(company);
    }

    public List<Companies> getAll() {
        return companiesRepository.findAll();
    }

    public Optional<Companies> getById(int companyId) {
        return companiesRepository.findById(companyId);
    }

    public void update(int companyId, Companies updatedCompany) {
        Optional<Companies> existingCompanyOptional = companiesRepository.findById(companyId);
        if (existingCompanyOptional.isPresent()) {
            Companies existingCompany = existingCompanyOptional.get();

            // Update fields here
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setTicker(updatedCompany.getTicker());
            existingCompany.setGrade(updatedCompany.getGrade());
            existingCompany.setLast_date_fetched(updatedCompany.getLast_date_fetched());
            companiesRepository.save(existingCompany);
        } else {
            throw new RuntimeException("Company not found with id: " + companyId);
        }
    }

    public void delete(int companyId) {
        companiesRepository.deleteById(companyId);
        //TODO Handle company not found case
    }

    public Optional<Companies> getByTicker(String ticker) {
        return companiesRepository.findByTicker(ticker);
    }
}
