package com.sterniczuk.eMail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class SendVerificationEmail {

    @Autowired
    private JavaMailSender javaMailSender;





    public void sendMail(String to, String Subject, String text)  throws Exception {

            /*SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("portalpodatkowy@server168390.nazwa.pl");
            message.setTo(to);
            message.setSubject(Subject);
            message.setText(text;
            javaMailSender.send(message);*/
       MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"UTF-8");
        helper.setTo(to);
        helper.setFrom("portalpodatkowy@server168390.nazwa.pl");
        helper.setSubject(Subject);
        helper.setText(text,true);
        javaMailSender.send(mimeMessage);

    }

    public String registrationTemplate(String token){

        String template =
                "<h4 style=\"text-align:center;\">Witamy w gronie, posidaczy Portalu Podatkowego</h4>"
                + "Kliknij w poniższy link aby aktywować swoje konto<br>"
                        + "http://localhost:8080/email/activate?token=" + token
                +"<br>Ta wiadomoścć została wygenerowana automatycznie, prosimy na nią nie odpowiadać.";
        return template;


    }


}