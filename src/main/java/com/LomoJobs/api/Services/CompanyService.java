package com.LomoJobs.api.Services;

import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Models.Job;
import com.LomoJobs.api.Repositories.CompanyRepository;
import com.LomoJobs.api.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    public Company registerCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(UUID id) {
        return companyRepository.findById(id);
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
}
