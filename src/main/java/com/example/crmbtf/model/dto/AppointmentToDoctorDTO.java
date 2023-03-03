package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.AppointmentToDoctors;
import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class AppointmentToDoctorDTO {

    private Date date;
    private LocalTime time;
    private String doctorSpeciality;
    private String doctorFirstName;
    private String doctorLastName;

    private Long doctorAppointmentsID;
    private Long clientID;
    private Long doctorID;
    private String patientFIO;

    public static AppointmentToDoctorDTO fromAppointment(AppointmentToDoctors appointment) {
        AppointmentToDoctorDTO appointmentToDoctorDTO = new AppointmentToDoctorDTO();
        appointmentToDoctorDTO.setDate(appointment.getDate());
        appointmentToDoctorDTO.setTime(appointment.getTime());
        appointmentToDoctorDTO.setDoctorFirstName(appointment.getDoctor().getFirstName());
        appointmentToDoctorDTO.setDoctorLastName(appointment.getDoctor().getLastName());
        appointmentToDoctorDTO.setDoctorLastName(appointmentToDoctorDTO.getDoctorLastName());
        appointmentToDoctorDTO.setDoctorSpeciality(appointment.getDoctor().getSpeciality());
        appointmentToDoctorDTO.setDoctorAppointmentsID(appointment.getDoctorsappointmentsID());
        appointmentToDoctorDTO.setClientID(appointment.getPatient().getId());
        appointmentToDoctorDTO.setDoctorID(appointment.getDoctor().getId());
        appointmentToDoctorDTO.setPatientFIO(appointment.getPatient().getFio());
        return appointmentToDoctorDTO;
    }
}
