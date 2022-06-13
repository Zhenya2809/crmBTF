package com.example.crmbtf.service;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.telegram.ExecutionContext;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AppointmentService {
    void createAppointmentToDoctors(String date, Time time, String doctorID);
    void createAppointmentTDoctors(String email, String date, Time time, String doctorID, ExecutionContext executionContext);
    void sendEmailReminder();
    List<AppointmentToDoctors> findAll();
    Iterable<AppointmentToDoctors> findAppointmentToDoctorsByDoctor(Doctor doctor);
    List<AppointmentToDoctors> findAllByDoctor_Id(Long id);
    HashMap<Date, List<String>> findAllAvailableTimeByDoctorId(Long id);
    List<AppointmentToDoctors> findAllByPatientId(Long id);
    void deleteAppointmentByDoctorId(Long id);
    void saveAppointments(Long id, String date, String time, String doctorID);

}
