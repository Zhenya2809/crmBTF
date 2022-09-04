package com.example.crmbtf.rest;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.dto.AuthenticationRequestDto;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.PatientDTO;
import com.example.crmbtf.security.jwt.JwtTokenProvider;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/doctor/")
public class DoctorRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public DoctorRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

//    @GetMapping(value = "myappointments")
//    public ResponseEntity<AppointmentService> myProfile() {
//
//
//        return ResponseEntity.ok(list);
//    }

}
