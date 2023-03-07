package com.example.crmbtf.rest;

import com.example.crmbtf.mapper.ConfigMapper;
import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.*;
import com.example.crmbtf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final ConfigMapper configMapper;


    @Autowired
    public UserRestControllerV1(UserService userService, DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, ConfigMapper configMapper) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;

        this.configMapper = configMapper;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(configMapper.toUserDto(user), HttpStatus.OK);
    }


    @GetMapping(value = "myProfile")
    public ResponseEntity<List<PatientDto>> myProfile() {
        return ResponseEntity.ok(configMapper.toPatientDtoList(patientService.getMyProfile()));
    }

    @GetMapping(value = "/appointment/{date}/{docId}")
    public ResponseEntity<List<FreeDateForAppointment>> getFreeTimeDoctorByDay(@PathVariable(name = "docId") Long docId,
                                                                               @PathVariable(name = "date") String date) {
        String[] split = date.split("-");
        String newDate = "";
        String month = split[1];
        String day = split[2];
        if (split[1].split("").length != 2) {
            month = "0" + split[1];
        }
        if (split[2].split("").length != 2) {
            day = "0" + split[2];
        }
        newDate = split[0] + "-" + month + "-" + day;


        HashMap<Date, List<String>> allAvailableTimeByDoctorId = appointmentService.findAllAvailableTimeByDoctorId(docId);
        List<String> freeTimeToAppointmentForDays = userService.freeTimeToAppointmentForDay(LocalDate.parse(newDate), docId, allAvailableTimeByDoctorId);
        List<FreeDateForAppointment> freeTimeForDate = new ArrayList<>();
        for (String time : freeTimeToAppointmentForDays) {
            FreeDateForAppointment freeDateForAppointment = new FreeDateForAppointment();
            freeDateForAppointment.setTime(time);
            freeTimeForDate.add(freeDateForAppointment);
        }

        return ResponseEntity.ok(freeTimeForDate);
    }

    @GetMapping(value = "/choseDoc/{speciality}")
    public ResponseEntity<List<DoctorDto>> choseDoc(@PathVariable(name = "speciality") String speciality) {
        List<Doctor> doctors = doctorService.findDoctorsBySpeciality(speciality);
        return ResponseEntity.ok(configMapper.toDoctorDtos(doctors));
    }

    @GetMapping(value = "/getdate/{docId}/{date}/{time}")
    public void createAppointment(
            @PathVariable(name = "docId") String docId,
            @PathVariable(name = "date") String date,
            @PathVariable(name = "time") String time) {
        appointmentService.createAppointmentToDoctorsBySite(date, time, Long.valueOf(docId));
    }

    @GetMapping(value = "doctors/search")
    public ResponseEntity<List<DoctorDto>> searchAllDoctors() {
        List<Doctor> doctors = doctorService.findAll().stream().toList();
        return ResponseEntity.ok(configMapper.toDoctorDtos(doctors));
    }

    @PostMapping(value = "update")
    public void updateDate(@RequestBody PatientDto patientDto) {
        patientService.updateProfile(patientDto);
    }

    @GetMapping(value = "doctors/getSpeciality")
    public ResponseEntity<List<Speciality>> getDrSpeciality() {
        Set<String> setSpeciality = doctorService.getDrSpeciality();

        List<String> drSpecialityDTO = new ArrayList<>(setSpeciality);
        List<Speciality> specDTOlist = new ArrayList<>();
        for (String string : drSpecialityDTO) {
            Speciality speciality = new Speciality();
            speciality.setDocSpeciality(string);
            specDTOlist.add(speciality);
        }
        return ResponseEntity.ok(specDTOlist);
    }

    @GetMapping(value = "getAppointment")
    public ResponseEntity<List<AppointmentToDoctorDTO>> getAppointmentForUser() {
        List<AppointmentToDoctors> userAppointment = appointmentService.getUserAppointment();
        return ResponseEntity.ok(AppointmentToDoctorDTO.fromAppointmentList(userAppointment));
    }


}