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
    //public void addNewReceipt(Receipt receipt){

    @Test
    public void addNewReceiptTestPositive(){

        //given
        Receipt receipt = this.prepareReceipt();
        final String user  = "Insert into Users(NIP,eMail,password,companyName, REGON, address, idStatus, createdAt) VALUES('7131774585','bartek114@autograf.pl','$2a$10$gLeX0VmwXxqDHpbiZ2Cb0.PBfPWHwFSTmxbHMFNOU3UPrZuv8RHrq','Bartosz Sterniczuk','430591583','MINKOWICE 194B, 21-007 MINKOWICE','1','23.01.2020')";
        temaplate.update(user);
        log.info(receipt.getInvoiceNumber());
        //when
        receiptRepository.addNewReceipt(receipt);

        //then

    }

    private Receipt prepareReceipt(){

        Date date = new Date();
        Receipt receipt = new Receipt();
        receipt.setInvoiceNumber("1");
        receipt.setIdUser("1");
        receipt.setCustomerName("Bartosz Sterniczuk");
        receipt.setAddress("Politechnika Lubelska");
        receipt.setNettoPrice(149.00);
        receipt.setVAT(23);
        receipt.setInvoiceNumber("1");
        receipt.setDate(date);
        receipt.setType("1");

        return receipt;
    }
}
