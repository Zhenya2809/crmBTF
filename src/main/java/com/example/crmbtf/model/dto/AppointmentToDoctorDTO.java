package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Patient;
import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class AppointmentToDoctorDTO {

    private Date date;
    private LocalTime time;
    private String doctorSpeciality;
    private String doctorFIO;

    public static AppointmentToDoctorDTO fromPatient(AppointmentToDoctors appointment) {
        AppointmentToDoctorDTO appointmentToDoctorDTO = new AppointmentToDoctorDTO();
        appointmentToDoctorDTO.setDate(appointment.getDate());
        appointmentToDoctorDTO.setTime(appointment.getTime());
        appointmentToDoctorDTO.setDoctorFIO(appointment.getDoctor().getFio());
        appointmentToDoctorDTO.setDoctorSpeciality(appointment.getDoctor().getSpeciality());
        return appointmentToDoctorDTO;
    }
}
