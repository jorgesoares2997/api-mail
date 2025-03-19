package com.jorge.api_mail.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String to; // Destinatário
    private String subject; // Assunto
    private String body; // Corpo do email
}