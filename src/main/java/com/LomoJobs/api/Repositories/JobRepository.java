package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findByCompany(Company company);

}