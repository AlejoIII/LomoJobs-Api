package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}