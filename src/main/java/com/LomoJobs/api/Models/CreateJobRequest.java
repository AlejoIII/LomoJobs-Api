package com.LomoJobs.api.Models;

import java.util.UUID;

public class CreateJobRequest {
    private String title;
    private String category;
    private String level;
    private String location;
    private UUID companyId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
}

