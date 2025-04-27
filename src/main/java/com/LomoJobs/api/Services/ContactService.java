package com.LomoJobs.api.Services;

import com.LomoJobs.api.Models.Message;
import com.LomoJobs.api.Repositories.MessageRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactService {

    private final JavaMailSender mailSender;
    private final MessageRepository messageRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public ContactService(JavaMailSender mailSender, MessageRepository messageRepository) {
        this.mailSender = mailSender;
        this.messageRepository = messageRepository;
    }

    public void enviarCorreoYGuardar(UUID jobId, String empresaEmail, String nombreEstudiante, String emailEstudiante, String mensajeContenido) throws MessagingException {
        // Enviar correo
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(empresaEmail);
        helper.setSubject("Nuevo contacto de estudiante");
        helper.setReplyTo(emailEstudiante);

        String cuerpoHtml = "<p><strong>Nombre:</strong> " + nombreEstudiante + "</p>"
                + "<p><strong>Email:</strong> " + emailEstudiante + "</p>"
                + "<p><strong>Mensaje:</strong><br/>" + mensajeContenido + "</p>";

        helper.setText(cuerpoHtml, true);

        mailSender.send(mensaje);

        // Guardar mensaje en la base de datos
        Message newMessage = new Message();
        newMessage.setJobId(jobId);
        newMessage.setSenderName(nombreEstudiante);
        newMessage.setSenderEmail(emailEstudiante);
        newMessage.setContent(mensajeContenido);

        messageRepository.save(newMessage);
    }
}
