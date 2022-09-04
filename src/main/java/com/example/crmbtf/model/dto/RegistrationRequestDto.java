package com.example.crmbtf.model.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String rePassword;

}
