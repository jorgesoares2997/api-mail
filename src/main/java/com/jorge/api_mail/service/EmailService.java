package com.jorge.api_mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jorge.api_mail.model.EmailRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FIXED_EMAIL = "jorgesoares2997@gmail.com"; // Seu email fixo
    private static final String THANK_YOU_PT = "Obrigado por utilizar minha API de envio de emails!\n" +
            "Este projeto faz parte do meu portfólio e foi criado para demonstrar minhas habilidades em Spring Boot e JavaMail.\n"
            +
            "Abaixo está o conteúdo do seu email:\n\n";
    private static final String THANK_YOU_EN = "Thank you for using my email sending API!\n" +
            "This project is part of my portfolio and was built to showcase my skills in Spring Boot and JavaMail.\n" +
            "Below is the content of your email:\n\n";

    public void enviarEmail(EmailRequest emailRequest) {
        // Construir a mensagem com agradecimento
        String mensagemCompleta = THANK_YOU_PT + emailRequest.getBody() + "\n\n" +
                THANK_YOU_EN + emailRequest.getBody();

        // Enviar para o destinatário da requisição
        SimpleMailMessage mensagemParaDestinatario = new SimpleMailMessage();
        mensagemParaDestinatario.setTo(emailRequest.getTo());
        mensagemParaDestinatario.setSubject(emailRequest.getSubject());
        mensagemParaDestinatario.setText(mensagemCompleta);
        mensagemParaDestinatario.setFrom("seu.email@gmail.com"); // Deve corresponder ao spring.mail.username

        // Enviar para o email fixo (você)
        SimpleMailMessage mensagemParaJorge = new SimpleMailMessage();
        mensagemParaJorge.setTo(FIXED_EMAIL);
        mensagemParaJorge.setSubject(emailRequest.getSubject() + " [Cópia]");
        mensagemParaJorge.setText(mensagemCompleta);
        mensagemParaJorge.setFrom("seu.email@gmail.com");

        // Enviar os dois emails
        mailSender.send(mensagemParaDestinatario);
        mailSender.send(mensagemParaJorge);
    }
}