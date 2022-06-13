package com.example.pointini.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntrepriseRegisterDto {

    private String firstName;
    private String lastName;
    @Column(unique=true, nullable=false)
    private String email;
    private String username;
    private String password;
    private String libelle;
    private String numTel;


}
