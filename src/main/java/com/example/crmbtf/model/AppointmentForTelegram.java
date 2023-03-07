package com.example.crmbtf.model;

import lombok.Data;

@Data
public class AppointmentForTelegram {
    private Long docId;
    private Long chatId;
    private String date;
    private String time;

}
