package com.example.crmbtf.rest;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<AppointmentToDoctors>> myProfile() {

        List<AppointmentToDoctors> appointmentForDoctor = appointmentService.getAppointmentForDoctor();

        return ResponseEntity.ok(appointmentForDoctor);
    }

}
