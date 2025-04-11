package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Models.CreateJobRequest;
import com.LomoJobs.api.Models.Job;
import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Repositories.CompanyRepository;
import com.LomoJobs.api.Services.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@RequestBody CreateJobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setCategory(request.getCategory());
        job.setLevel(request.getLevel());
        job.setLocation(request.getLocation());

        // Aquí necesitas buscar la compañía por su ID
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Compañía no encontrada"));
        job.setCompany(company);

        return ResponseEntity.ok(jobService.createJob(job));
    }


    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}
