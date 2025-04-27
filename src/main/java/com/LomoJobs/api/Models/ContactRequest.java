package com.LomoJobs.api.Models;

import java.util.UUID;

public class ContactRequest {
    private UUID jobId; // Nuevo
    private String empresaEmail;
    private String nombre;
    private String email;
    private String mensaje;

    // Getters y Setters
    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public String getEmpresaEmail() {
        return empresaEmail;
    }

    public void setEmpresaEmail(String empresaEmail) {
        this.empresaEmail = empresaEmail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
