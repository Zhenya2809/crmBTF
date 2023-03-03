package com.example.crmbtf.service;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Doctor;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AppointmentService {
    void createAppointmentToDoctorsByTelegram(String date, String time, String doctorID, Long chatId);

    void createAppointmentToDoctorsBySite(String date, String time, Long doctorID);

    void sendEmailReminder();

    List<AppointmentToDoctors> findAll();

    Iterable<AppointmentToDoctors> findAppointmentToDoctorsByDoctor(Doctor doctor);

    public List<AppointmentToDoctors> getAppointmentForDoctor();

    List<AppointmentToDoctors> findAllByDoctor_Id(Long id);

    HashMap<Date, List<String>> findAllAvailableTimeByDoctorId(Long id);

    List<AppointmentToDoctors> findAllByPatientId(Long id);

    List<AppointmentToDoctors> getUserAppointment();

    void deleteAppointmentByDoctorId(Long id);

    void saveAppointments(Long id, String date, String time, String doctorID);

    List<String> freeTimeToAppointmentForDay(Long docId);

    String updateAppointment(Date date, LocalTime time,
                           Long doctorID,
                           Long clientID,
                           Long appointmentID);
}
