package com.example.crmbtf.service.impl;

import com.example.crmbtf.email.SendEmailTLS;
import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.Patient;
import com.example.crmbtf.repository.AppointmentRepository;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.repository.PatientRepository;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void createAppointmentToDoctors(String date, Time time, String doctorID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<Patient> patientOptional = patientRepository.findByEmail(auth.getName());

        if (patientOptional.isPresent()) {
            try {
                Patient patient = patientOptional.get();
                Doctor doctor = doctorRepository.findDoctorById(Long.valueOf(doctorID));
                AppointmentToDoctors incoming = new AppointmentToDoctors();
                incoming.setDate(java.sql.Date.valueOf(date));
                incoming.setTime(time);
                incoming.setDoctor(doctor);
                incoming.setPatient(patient);

                appointmentRepository.save(incoming);
                log.info("IN createAppointment - appointment: {} successfully registered", incoming);
            } catch (DataIntegrityViolationException e) {
                log.error(" ERROR ---->  this date/time/doctor already exists  <----");
            }

        }
    }

    @Override
    public void createAppointmentTDoctors(String email, String date, Time time, String doctorID, ExecutionContext executionContext) {
        Optional<Patient> patientOptional = patientRepository.findByEmail(email);

        if (patientOptional.isPresent()) {

            Patient patient = patientOptional.get();
            Doctor doctor = doctorRepository.findDoctorById(Long.valueOf(doctorID));
            AppointmentToDoctors incoming = new AppointmentToDoctors();
            incoming.setDate(java.sql.Date.valueOf(date));
            incoming.setTime(time);
            incoming.setDoctor(doctor);
            incoming.setPatient(patient);
            appointmentRepository.save(incoming);
            log.info("IN createAppointment - appointment: {} successfully registered", incoming);
        } else {
            TelegramUsers user = executionContext.getAuthorizationUser();
            Patient patient = new Patient();
            patient.setChatId(executionContext.getChatId());
            patient.setFio(user.getFirstName() + " " + user.getLastName());
            patient.setEmail(user.getEmail());
            patient.setPhoneNumber(user.getPhone());
            executionContext.getPatientService().save(patient);

            Doctor doctor = doctorRepository.findDoctorById(Long.valueOf(doctorID));
            AppointmentToDoctors incoming = new AppointmentToDoctors();
            incoming.setDate(java.sql.Date.valueOf(date));
            incoming.setTime(time);
            incoming.setDoctor(doctor);
            incoming.setPatient(patient);
            appointmentRepository.save(incoming);
            log.info("IN createAppointment - appointment: {} successfully registered", incoming);
        }
    }

    @Override
    public void sendEmailReminder() {
        SendEmailTLS sendEmailTLS = new SendEmailTLS();

        appointmentRepository.findAll().forEach(e -> {
            Date date = e.getDate();
            Time time = e.getTime();
            String email = e.getPatient().getEmail();
            Date todayDate = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");

            if (formatForDateNow.format(todayDate).equals(date.toString())) {
                sendEmailTLS.SendEmail("Clinic appointment reminder", email, "We remind you that you have an appointment with a clinic at " + time);
                log.info("IN sendEmailReminder email sending to email:{} for time: {}", email, time);
            }

        });
    }

    @Override
    public List<AppointmentToDoctors> findAll() {
        List<AppointmentToDoctors> result = appointmentRepository.findAll();
        log.info("IN findAll - {} appointmentToDoctors found", result.size());
        return result;
    }

    @Override
    public Iterable<AppointmentToDoctors> findAppointmentToDoctorsByDoctor(Doctor doctor) {
        List<AppointmentToDoctors> result = appointmentRepository.findAppointmentToDoctorsByDoctor(doctor);
        log.info("IN findAppointmentToDocByDoctor {} appointment found",result.size());
        return result;
    }

    @Override
    public List<AppointmentToDoctors> findAllByDoctor_Id(Long id) {
        List<AppointmentToDoctors> result = appointmentRepository.findAllByDoctor_Id(id);
        log.info("IN findAllByDocId {} appointment found ",result);
        return result;
    }

    @Override
    public HashMap<Date, List<String>> findAllAvailableTimeByDoctorId(Long id) {
        List<AppointmentToDoctors> allByDoctor_id = appointmentRepository.findAllByDoctor_Id(id);
        log.info("IN findAllAvailableTimeByDocId - time: {} found by id: {}",allByDoctor_id,id);
        return listToMap(allByDoctor_id);
    }

    @Override
    public List<AppointmentToDoctors> findAllByPatientId(Long id) {
        List<AppointmentToDoctors> result = appointmentRepository.findAllByPatientId(id);
        log.info("IN findAllPatientId {} appointment found ",result.size());
        return result;
    }

    @Override
    public void deleteAppointmentByDoctorId(Long id) {
        AppointmentToDoctors appointmentToDoctorsByDoctorsappointmentsID = appointmentRepository.findAppointmentToDoctorsByDoctorsappointmentsID(id);
        appointmentRepository.delete(appointmentToDoctorsByDoctorsappointmentsID);
        log.info("IN deleteAppointmentbyDocId - appointment with id: {} successfully deleted",id);

    }

    @Override
    public void saveAppointments(Long id, String date, String time, String doctorID) {
        AppointmentToDoctors appointment = appointmentRepository.findAppointmentToDoctorsByDoctorsappointmentsID(id);
        appointment.setDate(java.sql.Date.valueOf(date));
        appointment.setTime(Time.valueOf(time));
        Doctor doctor = doctorRepository.findDoctorById(Long.valueOf(doctorID));
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
        log.info("IN saveAppointments - appointment: {} successfully saved", appointment);
    }

    public static HashMap<Date, List<String>> listToMap(List<AppointmentToDoctors> list) {
        HashMap<Date, List<String>> map = new HashMap<>();
        for (AppointmentToDoctors appointment : list) {
            if (map.containsKey(appointment.getDate())) {
                List<String> timeList = map.get(appointment.getDate());
                timeList.add(appointment.getTime().toString());
            } else {
                ArrayList<String> timeList = new ArrayList<>();
                timeList.add(appointment.getTime().toString());
                map.put(appointment.getDate(), timeList);
            }
        }
        return map;
    }
}
