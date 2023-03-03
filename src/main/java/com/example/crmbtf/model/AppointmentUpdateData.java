package com.example.crmbtf.model;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class AppointmentUpdateData {
    private Date date;
    private LocalTime time;
    private Long doctorID;
    private Long clientID;
    private Long appointmentID;

}
