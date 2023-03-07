package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.Patient;
import lombok.Data;


@Data
public class PatientDto {

    private String id;
    private String fio;

    private String sex;

    private String birthday;

    private String placeOfResidence;

    private String insurancePolicy;

    private String email;

    private String phoneNumber;

}
