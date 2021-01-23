package com.sterniczuk;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
@Data
@Repository
public class User {

    private Long id;

    private String createdAt;

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

    private String idStatus;

    public User() {

    }
}
