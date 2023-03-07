package com.example.crmbtf.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;
    private String photo;
    private String about;
    private Long userId;
    private String login;
    private String password;
    private String rePassword;
    private String email;



}
