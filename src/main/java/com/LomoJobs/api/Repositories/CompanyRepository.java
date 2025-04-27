package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {






}