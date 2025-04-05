package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Models.Company;
import com.LomoJobs.api.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public Company registerCompany(@RequestBody Company company) {
        return companyService.registerCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}