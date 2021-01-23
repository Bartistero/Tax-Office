package com.sterniczuk.data;

import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Size;
import java.sql.SQLException;
@Slf4j
@SpringBootTest
public class JdbcUserRepositoryTest {

    @Autowired
    JdbcUserRepository userRepository;

    @Test
    public void InsertNewUserTestPositive() {

        //given
        boolean value = false;
        User user = this.prepareUser();

        //when
        try {
            value = userRepository.insertNewUser(user, "123");

        } catch (Exception e) {

            e.printStackTrace();
        }

        //then
        Assertions.assertEquals(value, true);

    }

    @Test
    public void checkOneDataInDataBaseTestPositive() {

        //given
        User user = prepareUser();
        InsertNewUserTestPositive();

        //when
        int idUser = userRepository.checkOneDataInDataBase("Users", "NIP", "7131774585");

        //then
        Assertions.assertEquals(idUser,1);
    }

    @Test
    public void checkOneDataInDataBaseTestNegative() {

        //given
        User user = prepareUser();

        //when
        int idUser = userRepository.checkOneDataInDataBase("Users", "NIP", "7131774585");

        //then
        Assertions.assertEquals(idUser,-2);
    }

    @Test
    public void getUserTestPositive(){

        //given
            this.InsertNewUserTestPositive();
             User user = new User();
        //when
             try {
                user = userRepository.getUser("7131774585");
            }catch (Exception e){
                log.info(e.getMessage());
            }
        //then
            Assertions.assertEquals(user.getCompanyName(),"Politechnika");

    }

    private User prepareUser() {

        User user = new User();
        user.setNIP("7131774585");
        user.setEMail("bartek114@autograf.pl");
        user.setPassword("zaq1@WSX");
        user.setCompanyName("Politechnika");
        user.setREGON("123456789");
        user.setAddress("Lublin");

        return user;
    }


}
