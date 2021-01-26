package com.sterniczuk.data;

import com.sterniczuk.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
public class JdbcReceiptRepositoryTest {

    @Autowired
    JdbcReceiptRepository receiptRepository;

    @Autowired
    JdbcTemplate temaplate;

    @Test
    public void getAllReceiptTestPositive() throws SQLException {

        //given
        LocalDate date = LocalDate.now();
        List<Receipt> receipts;
        final String user  = "Insert into Users(NIP,eMail,password,companyName, REGON, address, idStatus, createdAt) VALUES('7131774585','bartek114@autograf.pl','$2a$10$gLeX0VmwXxqDHpbiZ2Cb0.PBfPWHwFSTmxbHMFNOU3UPrZuv8RHrq','Bartosz Sterniczuk','430591583','MINKOWICE 194B, 21-007 MINKOWICE','1','23.01.2020')";
        final String receipt = "Insert into Receipt(INVOICENUMBER,IDUSERS,CUSTOMERNAME,ADDRESS,NETTOPRICE,VAT,INVOICETYPE,DATE) VALUES('1','1','Bartosz Sterniczuk', 'Świdnik ul. Racławiska 14', '150','23','1', '"+date+"')";

        //when
        temaplate.update(user);
        temaplate.update(receipt);

        log.info(date.toString());
        receipts = receiptRepository.getALLRecepit("1","01");

        //then
        log.info(String.valueOf(receipts.size()));
        Assertions.assertEquals(receipts.get(0).getIdUser(),"1");

    }
}
