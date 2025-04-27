package com.LomoJobs.api.Controllers;

import com.LomoJobs.api.Models.ContactRequest;
import com.LomoJobs.api.Services.ContactService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<String> enviarCorreoYGuardar(@RequestBody ContactRequest request) {
        try {
            contactService.enviarCorreoYGuardar(
                    request.getJobId(),
                    request.getEmpresaEmail(),
                    request.getNombre(),
                    request.getEmail(),
                    request.getMensaje()
            );
            return ResponseEntity.ok("Correo enviado y mensaje guardado exitosamente");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al enviar el correo");
        }
    }
}
