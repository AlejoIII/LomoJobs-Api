package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}