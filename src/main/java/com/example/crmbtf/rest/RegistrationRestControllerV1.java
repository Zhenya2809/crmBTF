package com.example.crmbtf.rest;

import com.example.crmbtf.model.dto.RegistrationRequestDto;
import com.example.crmbtf.service.PatientService;
import com.example.crmbtf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users/")
public class RegistrationRestControllerV1 {
    private final UserService userService;
    private final PatientService patientService;

    @Autowired

    public RegistrationRestControllerV1(UserService userService, PatientService patientService) {
        this.userService = userService;
        this.patientService = patientService;
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationRequestDto requestDto) {

        String response = userService.userRegistration(requestDto.getUsername(), requestDto.getPassword(), requestDto.getRePassword(), requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail());
        patientService.createNew(requestDto.getEmail(),requestDto.getFirstName()+" "+requestDto.getLastName(),requestDto.getUsername());
        return ResponseEntity.ok(response);

    }
}
