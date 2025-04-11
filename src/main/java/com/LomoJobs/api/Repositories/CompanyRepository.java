package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
    @Autowired
    private CompanyRepository companyRepository;

}