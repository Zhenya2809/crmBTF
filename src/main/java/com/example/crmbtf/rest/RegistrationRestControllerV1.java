package com.example.crmbtf.rest;

import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.RegistrationRequestDto;
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

    @Autowired
    public RegistrationRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody RegistrationRequestDto requestDto) {

        userService.userRegistration(requestDto.getUsername(), requestDto.getPassword(), requestDto.getRePassword(), requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail());
        Map<Object, Object> response = new HashMap<>();
        response.put("result", "User with username: "+requestDto.getUsername()+" successfully registered");
        return ResponseEntity.ok(response);

    }
}
