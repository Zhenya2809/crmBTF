package com.example.crmbtf.rest;

import com.example.crmbtf.mapper.ConfigMapper;
import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.*;
import com.example.crmbtf.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/telegram/")
public class TelegramRestControllerV1 {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final TelegramUsersService telegramUsersService;
    private final ConfigMapper configMapper;

    @Autowired
    public TelegramRestControllerV1(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, TelegramUsersService telegramUsersService, ConfigMapper configMapper) {
        this.doctorService = doctorService;

        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.telegramUsersService = telegramUsersService;
        this.configMapper = configMapper;
    }

    @GetMapping(value = "/getAppointmentsToDoctor/{chatId}")
    public ResponseEntity<List<AppointmentToDoctorDTO>> getPatientByChatIdAndGetAppointment(@PathVariable(name = "chatId") String chatId) {

        Patient patientByEmail = patientService.findPatientByEmailOrCreatePatient(telegramUsersService.findDataUserByChatId(Long.valueOf(chatId))
                .get().getEmail(), Long.valueOf(chatId));
        List<AppointmentToDoctors> userAppointment = appointmentService.findAllByPatientId(patientByEmail.getId());

        return ResponseEntity.ok(configMapper.toAppointmentToDoctorDtos(userAppointment));


    }

    @GetMapping(value = "/getPatient/{chatId}")
    public ResponseEntity<PatientDto> setPatient(@PathVariable(name = "chatId") String chatId) {
        Patient patientByEmail = patientService.findPatientByEmailOrCreatePatient(
                telegramUsersService.findDataUserByChatId(Long.valueOf(chatId)).get().
                        getEmail(), Long.valueOf(chatId));
        return ResponseEntity.ok(configMapper.toPatientDto(patientByEmail));
    }

    @GetMapping(value = "/getAllSpeciality")
    public ResponseEntity<List<String>> getAllSpeciality() {
        List<String> allSpeciality = doctorService.getAllSpeciality();
        return ResponseEntity.ok(allSpeciality);
    }

    @GetMapping(value = "/finddoctor/{speciality}")
    public ResponseEntity<List<DoctorDto>> findDoctorBySpeciality(@PathVariable(name = "speciality") String speciality) {
        List<Doctor> doctorsBySpeciality = doctorService.findDoctorsBySpeciality(speciality);
        return ResponseEntity.ok(configMapper.toDoctorDtos(doctorsBySpeciality));
    }

    @GetMapping(value = "/finddoctorBy/{firstName}/{lastName}")
    public ResponseEntity<DoctorDto> findDoctorByFio(@PathVariable(name = "firstName") String firstName,
                                                     @PathVariable(name = "lastName") String lastName) {
        Doctor doctorByFio = doctorService.findDoctorByFio(firstName, lastName);
        return ResponseEntity.ok(configMapper.toDoctorDto(doctorByFio));
    }

    @GetMapping(value = "/findavailabletime/{docId}")
    public ResponseEntity<List<String>> findAvailableTime(@PathVariable(name = "docId") String docId) {
        List<String> stringList = appointmentService.freeTimeToAppointmentForDay(Long.valueOf(docId));
        return ResponseEntity.ok(stringList);
    }

    @PostMapping(value = "/createAppointment")
    public ResponseEntity<List<String>> createAppointment(@RequestBody AppointmentForTelegram appointmentForTelegram) {
        String time = appointmentForTelegram.getTime();
        String date = appointmentForTelegram.getDate();
        Long docId = appointmentForTelegram.getDocId();
        Long chatId = appointmentForTelegram.getChatId();

        appointmentService.createAppointmentToDoctorsByTelegram(date, time, String.valueOf(docId), chatId);
        return ResponseEntity.ok(null);
    }
}
