package com.LomoJobs.api.Auth;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;
    private String role;
    private UUID companyId;

    public AuthResponse(String token, UUID userId, String role, UUID companyId) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.companyId = companyId;
    }

    public String getToken() {
        return token;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public UUID getCompanyId() {
        return companyId;
    }
}
