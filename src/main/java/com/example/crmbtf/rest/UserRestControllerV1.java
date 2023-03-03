package com.example.crmbtf.rest;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.dto.*;
import com.example.crmbtf.model.User;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.service.DoctorService;
import com.example.crmbtf.service.PatientService;
import com.example.crmbtf.service.UserService;
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

    @Autowired
    public UserRestControllerV1(UserService userService, DoctorService doctorService, PatientService patientService, AppointmentService appointmentService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "myProfile")
    public ResponseEntity<List<PatientDTO>> myProfile() {
        return ResponseEntity.ok(patientService.getMyProfile());
    }

    @GetMapping(value = "/appointment/{date}/{docId}")
    public ResponseEntity<List<FreeDateToAppointmentDTO>> getFreeTimeDoctorByDay(@PathVariable(name = "docId") Long docId,
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
        List<FreeDateToAppointmentDTO> freeTimeForDate = new ArrayList<>();
        for (String time : freeTimeToAppointmentForDays) {
            FreeDateToAppointmentDTO freeDateToAppointmentDTO = new FreeDateToAppointmentDTO();
            freeDateToAppointmentDTO.setTime(time);
            freeTimeForDate.add(freeDateToAppointmentDTO);
        }
        System.out.println(freeTimeForDate);
        return ResponseEntity.ok(freeTimeForDate);
    }

    @GetMapping(value = "/choseDoc/{speciality}")
    public ResponseEntity<List<DoctorDto>> choseDoc(@PathVariable(name = "speciality") String speciality) {
        List<Doctor> doctors = doctorService.findDoctorsBySpeciality(speciality);
        List<DoctorDto> doctorDtoList = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorDto doctorDto = DoctorDto.fromDoctor(doctor);
            doctorDtoList.add(doctorDto);
        }
        return ResponseEntity.ok(doctorDtoList);
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
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        List<Doctor> doctors = doctorService.findAll().stream().toList();

        for (Doctor doctor : doctors) {
            DoctorDto doctorDto = DoctorDto.fromDoctor(doctor);
            doctorDtoList.add(doctorDto);

        }
        return ResponseEntity.ok(doctorDtoList);
    }

    @PostMapping(value = "update")
    public void updateDate(@RequestBody PatientUpdateDTO patientUpdate) {
        patientService.updateProfile(patientUpdate);
    }

    @GetMapping(value = "doctors/getSpeciality")
    public ResponseEntity<List<SpecialityDTO>> getDrSpeciality() {
        Set<String> setSpeciality = doctorService.getDrSpeciality();
        List<String> drSpecialityDTO = new ArrayList<>(setSpeciality);
        List<SpecialityDTO> specDTOlist = new ArrayList<>();
        for (String string : drSpecialityDTO) {
            SpecialityDTO specialityDTO = new SpecialityDTO();
            specialityDTO.setDocSpeciality(string);
            specDTOlist.add(specialityDTO);
        }
        return ResponseEntity.ok(specDTOlist);
    }

    @GetMapping(value = "getAppointment")
    public ResponseEntity<List<AppointmentToDoctorDTO>> getAppointmentForUser() {
        List<AppointmentToDoctors> userAppointment = appointmentService.getUserAppointment();
        List<AppointmentToDoctorDTO> appointmentToDoctorDTOList = new ArrayList<>();
        for (AppointmentToDoctors appointment : userAppointment) {
            AppointmentToDoctorDTO appointmentToDoctorDTO1 = AppointmentToDoctorDTO.fromAppointment(appointment);
            appointmentToDoctorDTOList.add(appointmentToDoctorDTO1);
        }
        return ResponseEntity.ok(appointmentToDoctorDTOList);
    }
}