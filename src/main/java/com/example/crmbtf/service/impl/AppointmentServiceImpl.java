package com.example.crmbtf.service.impl;

import com.example.crmbtf.email.SendEmailTLS;
import com.example.crmbtf.model.*;
import com.example.crmbtf.repository.AppointmentRepository;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.repository.PatientRepository;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final UserRepository userRepository;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public void createAppointmentToDoctors(String date, Time time, String doctorID) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Optional<Patient> patientOptional = patientRepository.findByEmail(auth.getName());
//
//        if (patientOptional.isPresent()) {
//            try {
//                Patient patient = patientOptional.get();
//                Optional<Doctor> doctorById = doctorRepository.findDoctorById(Long.valueOf(doctorID));
//                if (doctorById.isPresent()) {
//                    AppointmentToDoctors incoming = new AppointmentToDoctors();
//                    incoming.setDate(java.sql.Date.valueOf(date));
//                    incoming.setTime(time);
//                    incoming.setDoctor(doctorById.get());
//                    incoming.setPatient(patient);
//
//                    appointmentRepository.save(incoming);
//                    log.info("IN createAppointment - appointment: {} successfully registered", incoming);
//                }
//            } catch (DataIntegrityViolationException e) {
//                log.error(" ERROR ---->  this date/time/doctor already exists  <----");
//            }
//
//        }
//    }

    @Override
    public void createAppointmentToDoctorsByTelegram(String email, String date, String time, String doctorID, ExecutionContext executionContext) {
        Optional<Patient> patientOptional = patientRepository.findByEmail(email);

        if (patientOptional.isPresent()) {

            Patient patient = patientOptional.get();
            Optional<Doctor> doctorById = doctorRepository.findDoctorById(Long.valueOf(doctorID));
            if (doctorById.isPresent()) {
                AppointmentToDoctors incoming = new AppointmentToDoctors();
                incoming.setDate(java.sql.Date.valueOf(date));
                incoming.setTime(LocalTime.parse(time));
                incoming.setDoctor(doctorById.get());
                incoming.setPatient(patient);
                appointmentRepository.save(incoming);
                log.info("IN createAppointment - appointment: {} successfully registered", incoming);
            }
        } else {
            TelegramUsers user = executionContext.getAuthorizationUser();
            Patient patient = new Patient();
            patient.setChatId(executionContext.getChatId());
            patient.setFio(user.getFirstName() + " " + user.getLastName());
            patient.setEmail(user.getEmail());
            patient.setPhoneNumber(user.getPhone());
            executionContext.getPatientService().save(patient);

            Optional<Doctor> doctorById = doctorRepository.findDoctorById(Long.valueOf(doctorID));
            if (doctorById.isPresent()) {
                AppointmentToDoctors incoming = new AppointmentToDoctors();
                incoming.setDate(java.sql.Date.valueOf(date));
                incoming.setTime(LocalTime.parse(time));
                incoming.setDoctor(doctorById.get());
                incoming.setPatient(patient);
                appointmentRepository.save(incoming);
                log.info("IN createAppointment - appointment: {} successfully registered", incoming);
            }
        }
    }

    @Override
    public void createAppointmentToDoctorsBySite(String date, String time, Long doctorID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Patient patient;
        Optional<User> byUsername = userRepository.findByUsername(auth.getName());
        if (byUsername.isPresent()) {
            String email = byUsername.get().getEmail();
            Optional<Patient> byEmail = patientRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                patient = byEmail.get();
            } else {
                patient = new Patient();
                patient.setFio(byUsername.get().getFirstName() + " " + byUsername.get().getLastName());
                patient.setEmail(byUsername.get().getEmail());
                patientRepository.save(patient);
            }
            Optional<Doctor> doctorById = doctorRepository.findDoctorById(doctorID);
            if (doctorById.isPresent()) {

                Doctor doctor = doctorById.get();

                AppointmentToDoctors appointment = new AppointmentToDoctors();
                appointment.setDate(java.sql.Date.valueOf(date));
                appointment.setDoctor(doctor);
                appointment.setPatient(patient);
                appointment.setTime(LocalTime.parse(time));
                appointmentRepository.save(appointment);
                log.info("IN createAppointmentToDoctorsBySite - appointment: {} successfully created", appointment);
            }
        }
    }


    @Override
    public void sendEmailReminder() {
        SendEmailTLS sendEmailTLS = new SendEmailTLS();

        appointmentRepository.findAll().forEach(e -> {
            Date date = e.getDate();
            LocalTime time = e.getTime();
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
        log.info("IN findAppointmentToDocByDoctor {} appointment found", result.size());
        return result;
    }

    @Override
    public List<AppointmentToDoctors> findAllByDoctor_Id(Long id) {
        List<AppointmentToDoctors> result = appointmentRepository.findAllByDoctor_Id(id);
        log.info("IN findAllByDocId {} appointment found ", result);
        return result;
    }

    @Override
    public HashMap<Date, List<String>> findAllAvailableTimeByDoctorId(Long id) {
        List<AppointmentToDoctors> allByDoctor_id = appointmentRepository.findAllByDoctor_Id(id);
        log.info("IN findAllAvailableTimeByDocId - time: {} found by id: {}", allByDoctor_id, id);
        return listToMap(allByDoctor_id);
    }

    @Override
    public List<AppointmentToDoctors> findAllByPatientId(Long id) {
        List<AppointmentToDoctors> result = appointmentRepository.findAllByPatientId(id);
        log.info("IN findAllPatientId {} appointment found ", result.size());
        return result;
    }

    public List<AppointmentToDoctors> getUserAppointment() {
        List<AppointmentToDoctors> appointmentToDoctors = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> byUsername = userRepository.findByUsername(auth.getName());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            Optional<Patient> byEmail = patientRepository.findByEmail(user.getEmail());
            if (byEmail.isPresent()) {
                Patient patient = byEmail.get();
                appointmentToDoctors = patient.getAppointmentToDoctors();
            }
        }

        return appointmentToDoctors;
    }

    @Override
    public void deleteAppointmentByDoctorId(Long id) {
        AppointmentToDoctors appointmentToDoctorsByDoctorsappointmentsID = appointmentRepository.findAppointmentToDoctorsByDoctorsappointmentsID(id);
        appointmentRepository.delete(appointmentToDoctorsByDoctorsappointmentsID);
        log.info("IN deleteAppointmentbyDocId - appointment with id: {} successfully deleted", id);

    }

    @Override
    public void saveAppointments(Long id, String date, String time, String doctorID) {
        AppointmentToDoctors appointment = appointmentRepository.findAppointmentToDoctorsByDoctorsappointmentsID(id);
        appointment.setDate(java.sql.Date.valueOf(date));
        appointment.setTime(LocalTime.parse(time));
        Optional<Doctor> doctorById = doctorRepository.findDoctorById(Long.valueOf(doctorID));
        if (doctorById.isPresent()) {
            appointment.setDoctor(doctorById.get());
            appointmentRepository.save(appointment);
            log.info("IN saveAppointments - appointment: {} successfully saved", appointment);
        }
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
