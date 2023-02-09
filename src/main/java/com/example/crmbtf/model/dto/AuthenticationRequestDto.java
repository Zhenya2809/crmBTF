package com.example.crmbtf.model.dto;

import lombok.Data;


@Data
public class AuthenticationRequestDto {
    private String phone;
    private String password;
}