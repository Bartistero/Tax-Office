package com.sterniczuk.data;

import com.sterniczuk.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcUserRepositoryTest {

    @Autowired
    JdbcUserRepository userRepository;

    @Test
    public void InsertNewUserTest(){

        //given

        User user = this.prepareUser();
        //when

      //  userRepository.insertNewUser(user,"123");
        //then


    }

        private User prepareUser(){

            User user = new User();
            user.setNIP("7131774585");
            user.setEMail("bartek114@autograf.pl");
            user.setPassword("zaq1@WSX");
            user.setCompanyName("Politechnika");
            user.setREGON("123456789");
            user.setAddress("LUblin");

            return user;
        }


}
