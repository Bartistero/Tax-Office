package com.sterniczuk;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class User {
    @NotNull
    @Size(min=11, max=11)
    private  String NIP;

   @Email
    private  String eMail;

    @NotNull
    private  String password;

    @NotNull
    private String companyName;

    @NotNull
    private  String REGON;

    @NotNull
    private  String address;

    public User() {

    }
}
