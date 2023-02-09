package com.example.crmbtf.rest;

import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.*;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/telegram/")
public class TelegramRestControllerV1 {
    private final UserRepository userRepository;
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final TelegramUsersService telegramUsersService;

    @Autowired
    public TelegramRestControllerV1(UserService userService, DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, TelegramUsersService telegramUsersService,
                                    UserRepository userRepository) {
        this.userService = userService;
        this.doctorService = doctorService;

        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.telegramUsersService = telegramUsersService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getAppointmentsToDoctor/{chatId}")
    public ResponseEntity<List<AppointmentToDoctorDTO>> getPatientByChatIdAndGetAppointment(@PathVariable(name = "chatId") String chatId) {

        Patient patientByEmail = patientService.findPatientByEmailOrCreatePatient(telegramUsersService.findDataUserByChatId(Long.valueOf(chatId)).get().getEmail(), Long.valueOf(chatId));

        List<AppointmentToDoctors> userAppointment = appointmentService.findAllByPatientId(patientByEmail.getId());


        List<AppointmentToDoctorDTO> appointmentToDoctorDTOList = new ArrayList<>();

        for (AppointmentToDoctors appointment : userAppointment) {
            AppointmentToDoctorDTO appointmentToDoctorDTO1 = AppointmentToDoctorDTO.fromPatient(appointment);
            appointmentToDoctorDTOList.add(appointmentToDoctorDTO1);
        }
        return ResponseEntity.ok(appointmentToDoctorDTOList);


    }

    @GetMapping(value = "/getPatient/{chatId}")
    public ResponseEntity<PatientDTO> setPatient(@PathVariable(name = "chatId") String chatId) {
        Patient patientByEmail = patientService.findPatientByEmailOrCreatePatient(telegramUsersService.findDataUserByChatId(Long.valueOf(chatId)).get().getEmail(), Long.valueOf(chatId));
        PatientDTO patientDTO = PatientDTO.fromPatient(patientByEmail);


        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping(value = "/getAllSpeciality")
    public ResponseEntity<List<String>> getAllSpeciality() {
        List<String> allSpeciality = doctorService.getAllSpeciality();
        return ResponseEntity.ok(allSpeciality);
    }

    @GetMapping(value = "/finddoctor/{speciality}")
    public ResponseEntity<List<DoctorDto>> findDoctorBySpeciality(@PathVariable(name = "speciality") String speciality) {
        List<Doctor> doctorsBySpeciality = doctorService.findDoctorsBySpeciality(speciality);
        List<DoctorDto> doctorDtoList = DoctorDto.fromDoctorList(doctorsBySpeciality);
        return ResponseEntity.ok(doctorDtoList);
    }

    @GetMapping(value = "/finddoctorBy/{firstName}/{lastName}")
    public ResponseEntity<DoctorDto> findDoctorByFio(@PathVariable(name = "firstName") String firstName,
                                                     @PathVariable(name = "lastName") String lastName) {
        Doctor doctorByFio = doctorService.findDoctorByFio(firstName, lastName);
        return ResponseEntity.ok(DoctorDto.fromDoctor(doctorByFio));
    }

    @GetMapping(value = "/findavailabletime/{docId}")
    public ResponseEntity<List<String>> findAvailableTime(@PathVariable(name = "docId") String docId) {
        List<String> stringList = appointmentService.freeTimeToAppointmentForDay(Long.valueOf(docId));
        return ResponseEntity.ok(stringList);
    }

    @PostMapping(value = "/createAppointment")
    public ResponseEntity<List<String>> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        String time = appointmentDto.getTime();
        String date = appointmentDto.getDate();
        Long docId = appointmentDto.getDocId();
        Long chatId = appointmentDto.getChatId();

        appointmentService.createAppointmentToDoctorsByTelegram(date, time, String.valueOf(docId), chatId);
        //appointmentService.createAppointmentToDoctorsByTelegram()
        return ResponseEntity.ok(null);
    }


//    @GetMapping(value = "/getAllAvailableTimeByDoctorId/{doctId}/{day}")
//    public ResponseEntity<List<String>> getAvailableTime(@PathVariable(name = "doctId") String doctId,
//                                                         @PathVariable(name = "day") String day) {
//        LocalDate localDate = LocalDate.parse(day);
//
//        List<String> stringList = executionContext.freeTimeToAppointmentForDay(localDate, Long.valueOf(doctId));
//        return ResponseEntity.ok(stringList);
//findDoctorByFio
//    }
}
