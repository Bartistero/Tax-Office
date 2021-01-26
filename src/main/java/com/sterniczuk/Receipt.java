package com.sterniczuk;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Data
@Repository
public class Receipt {

    private Long id;

    private String invoiceNumber;

    private String idUser;

    private String customerName;

    private String address;

    private float nettoPrice;

    private int VAT;

    private String type;

    private Date date;

    private String month;

}
