package com.example.crmbtf.model.dto;

import lombok.Data;

@Data
public class AppointmentDto {
    private Long docId;
    private Long chatId;
    private String date;
    private String time;

}
