package com.jorge.api_mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jorge.api_mail.model.EmailRequest;
import com.jorge.api_mail.service.EmailService;

@RestController
@RequestMapping("/api/public/emails")
public class PublicEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.enviarEmail(emailRequest);
            return ResponseEntity.ok("Email enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar email: " + e.getMessage());
        }
    }
}