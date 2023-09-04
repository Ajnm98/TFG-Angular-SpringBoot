package com.example.springboottfg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


        @Autowired
        private JavaMailSender javaMailSender;

        public void sendMail(String from, String to, String subject, String body) {

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
        }
    }
