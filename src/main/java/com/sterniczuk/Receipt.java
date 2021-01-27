package com.sterniczuk;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Repository
public class Receipt {

    private Long id;

    private String invoiceNumber;

    private String idUser;

    @NotNull
    private String customerName;

    @NotNull
    private String address;

    @NotNull
    private double nettoPrice;

    @NotNull
    private int VAT;

    @NotNull
    private String type;

    private Date date;

    @NotNull
    private String customerNIP;

}
