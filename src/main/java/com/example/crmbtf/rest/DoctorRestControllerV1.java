package com.example.crmbtf.rest;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.dto.AppointmentToDoctorDTO;
import com.example.crmbtf.model.AppointmentUpdateData;
import com.example.crmbtf.model.dto.DoctorDto;
import com.example.crmbtf.model.dto.PatientDTO;
import com.example.crmbtf.model.dto.UserDto;
import com.example.crmbtf.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/doctor/")
public class DoctorRestControllerV1 {
    private final AppointmentService appointmentService;

    @Autowired
    public DoctorRestControllerV1(AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "appointments")
    public ResponseEntity<List<AppointmentToDoctorDTO>> myProfile() {

        List<AppointmentToDoctors> appointmentForDoctor = appointmentService.getAppointmentForDoctor();
        List<AppointmentToDoctorDTO> appointmentToDoctorDTOList = new ArrayList<>();
        for (AppointmentToDoctors appointmentToDoctors : appointmentForDoctor) {
            AppointmentToDoctorDTO appointmentToDoctorDTO = AppointmentToDoctorDTO.fromAppointment(appointmentToDoctors);
            appointmentToDoctorDTOList.add(appointmentToDoctorDTO);
        }
        return ResponseEntity.ok(appointmentToDoctorDTOList);
    }
    @PostMapping("updateAppointment")
    public ResponseEntity<String> updateAppointment(@RequestBody AppointmentUpdateData requestDto) {

        Long appointmentID = requestDto.getAppointmentID();
        Long doctorID = requestDto.getDoctorID();
        Long clientID = requestDto.getClientID();
        LocalTime time = requestDto.getTime();
        Date date = requestDto.getDate();
        String response = appointmentService.updateAppointment(date, time, doctorID, clientID, appointmentID);
        return ResponseEntity.ok(response);
    }
}
