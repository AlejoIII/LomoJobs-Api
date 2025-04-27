package com.LomoJobs.api.Services;

import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Models.Job;
import com.LomoJobs.api.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job, Company company) {
        job.setCompany(company);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByCompany(Company company) {
        return jobRepository.findByCompany(company);
    }

    public Optional<Job> getJobById(UUID jobId) {
        return jobRepository.findById(jobId);
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    public boolean deleteJob(UUID jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        if (job.isPresent()) {
            jobRepository.delete(job.get());
            return true;
        }
        return false;
    }
}