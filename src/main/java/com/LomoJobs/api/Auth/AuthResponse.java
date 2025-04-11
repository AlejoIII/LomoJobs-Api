package com.LomoJobs.api.Auth;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;
    private String role;

    public AuthResponse(String token, UUID userId, String role) {
        this.token = token;
        this.userId = userId;
        this.role = role;
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
}
