package com.example.crmbtf.model.dto;

import lombok.Data;

@Data
public class TreatmentDto {
    private Long clientID;
    private String diagnosis;
    private String recommendations;
    private String symptoms;
    private String treatment;
    private Long doctorID;

}
