package com.sterniczuk.MF;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sterniczuk.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import org.junit.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.event.ExceptionEvent;

import java.util.Map;
@Slf4j
public class GetDataFromMftest {

    @Test
    public void checkNIPTest_Positive() throws Exception {

        //given
        GetDataFromMf check = new GetDataFromMf();

        //when
        Map<String,String> map  = check.checkNIP("7131774585");

        //then
        Assertions.assertEquals(map.get("name"), "ADAM STERNICZUK" );
        Assertions.assertEquals(map.get("NIP"), "7131774585" );
        Assertions.assertEquals(map.get("REGON"), "430591583" );
        Assertions.assertEquals(map.get("address"), "MINKOWICE 194B, 21-007 MINKOWICE");

        }

    @Test
    public void checkNIPTest_throwsException() throws Exception {

        //given
        GetDataFromMf check = new GetDataFromMf();

        //then
        Assertions.assertThrows(Exception.class, () -> check.checkNIP("7131774546"));

    }


}
