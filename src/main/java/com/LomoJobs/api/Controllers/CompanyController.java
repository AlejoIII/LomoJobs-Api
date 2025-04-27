package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Models.Job;
import com.LomoJobs.api.Repositories.JobRepository;
import com.LomoJobs.api.Services.CompanyService;
import com.LomoJobs.api.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/register")
    public Company registerCompany(@RequestBody Company company) {
        return companyService.registerCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<?> updateJob(
            @PathVariable UUID companyId,
            @PathVariable UUID jobId,
            @RequestBody Job job
    ) {
        Optional<Company> companyOpt = companyService.getCompanyById(companyId);
        if (companyOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Company company = companyOpt.get();

        Optional<Job> jobOpt = jobService.getJobById(jobId);
        if (jobOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajo no encontrado");
        }

        Job existingJob = jobOpt.get();
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());

        existingJob.setCompany(company);

        Job updatedJob = jobService.updateJob(existingJob);
        if (updatedJob == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el trabajo");
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedJob);
    }

    @PostMapping("/{companyId}/jobs")
    public ResponseEntity<?> createJob(
            @PathVariable UUID companyId,
            @RequestBody Job job
    ) {
        Optional<Company> companyOpt = companyService.getCompanyById(companyId);

        if (companyOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Company company = companyOpt.get();
        job.setCompany(company);
        Job savedJob = jobService.createJob(job, company);
        if (savedJob == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el trabajo");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
    }

    @DeleteMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<?> deleteJob(
            @PathVariable UUID companyId,
            @PathVariable UUID jobId
    ) {
        Optional<Company> companyOpt = companyService.getCompanyById(companyId);
        if (companyOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
        }

        Company company = companyOpt.get();

        boolean isDeleted = jobService.deleteJob(jobId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajo no encontrado");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
