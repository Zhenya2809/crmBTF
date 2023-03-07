package com.example.crmbtf.rest;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.dto.RegistrationRequestDto;
import com.example.crmbtf.service.PatientCardService;
import com.example.crmbtf.service.PatientService;
import com.example.crmbtf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users/")
public class RegistrationRestControllerV1 {
    private final UserService userService;
    private final PatientService patientService;
    private final PatientCardService patientCardService;

    @Autowired

    public RegistrationRestControllerV1(UserService userService, PatientService patientService, PatientCardService patientCardService) {
        this.userService = userService;
        this.patientService = patientService;
        this.patientCardService = patientCardService;
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationRequestDto requestDto) {

        String response = userService.userRegistration(requestDto.getUsername(), requestDto.getPassword(), requestDto.getRePassword(), requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail());
        Patient aNew = patientService.createNew(requestDto.getEmail(), requestDto.getFirstName() + " " + requestDto.getLastName(), requestDto.getUsername());
        PatientCard patientCard = new PatientCard();
        patientCard.setPatient(aNew);
        patientCardService.save(patientCard);
        return ResponseEntity.ok(response);

    }
}
