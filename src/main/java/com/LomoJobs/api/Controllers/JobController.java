package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Models.CreateJobRequest;
import com.LomoJobs.api.Models.Job;
import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Models.User;
import com.LomoJobs.api.Repositories.CompanyRepository;
import com.LomoJobs.api.Repositories.UserRepository;
import com.LomoJobs.api.Services.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my")
    public ResponseEntity<?> getMyJobs(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getCompany() == null) {
            return ResponseEntity.status(403).body("Este usuario no tiene empresa asociada");
        }

        List<Job> jobs = jobService.getJobsByCompany(user.getCompany());
        return ResponseEntity.ok(jobs);
    }


    @GetMapping
    public List<Job> getAllJobs(Authentication authentication) {
        System.out.println("Usuario autenticado: " + authentication.getName());
        System.out.println("Roles: " + authentication.getAuthorities());
        return jobService.getAllJobs();
    }
}
