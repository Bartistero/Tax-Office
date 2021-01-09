package com.sterniczuk.eMail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendVerificationEmailTest {

    @Autowired
    private SendVerificationEmail eMail;

    @Test
    public void sendMailTest() {

            Assertions.assertDoesNotThrow(() -> eMail.sendMail("bartek114@autograf.pl","Potwierdzenie rejestracji na Portalu podatkowym", eMail.registrationTemplate("123")));

    }
}

