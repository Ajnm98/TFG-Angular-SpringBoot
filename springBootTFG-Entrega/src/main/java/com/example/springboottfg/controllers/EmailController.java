package com.example.springboottfg.controllers;

import com.example.springboottfg.models.Email;
import com.example.springboottfg.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {



    @Autowired
    private EmailService mailService;

//    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
//    public String sendMail(@RequestBody Email form){
//
//        String message = form.getContent() +"\n\n Datos de contacto: " +
//                "\nNombre: " + form.getSubject() + "\nE-mail: " + form.getEmail();
//
//        mailService.sendMail("pfernandezsalas@safareyes.es",Recibe,"Cita",message);
//
//        return "correo enviado";
//    }
}
