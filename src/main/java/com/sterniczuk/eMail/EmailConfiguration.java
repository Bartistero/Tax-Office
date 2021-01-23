package com.sterniczuk.eMail;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("server168390.nazwa.pl");

        mailSender.setUsername("portalpodatkowy@server168390.nazwa.pl");
        mailSender.setPassword("qwertY123456");

        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");


        props.put("mail.debug", "true");

        return mailSender;
    }
}
