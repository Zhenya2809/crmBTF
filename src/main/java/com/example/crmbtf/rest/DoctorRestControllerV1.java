package com.example.crmbtf.rest;

import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.AppointmentToDoctorDTO;
import com.example.crmbtf.model.dto.DocDto;
import com.example.crmbtf.model.dto.TreatmentDto;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.service.TreatmentInformationService;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/doctor/")
public class DoctorRestControllerV1 {
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final DoctorRepository doctorRepository;
    private final TreatmentInformationService treatmentInformationService;

    @Autowired
    public DoctorRestControllerV1(AppointmentService appointmentService, UserService userService, DoctorRepository doctorRepository, TreatmentInformationService treatmentInformationService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.doctorRepository = doctorRepository;
        this.treatmentInformationService = treatmentInformationService;
    }

    @GetMapping(value = "appointments")
    public ResponseEntity<List<AppointmentToDoctorDTO>> myProfile() {
        List<AppointmentToDoctors> appointmentForDoctor = appointmentService.findByDateGreaterThanEqual();
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

    @PostMapping("updateDrInformation")
    public void updateDoctorInformation(@RequestBody DocDto requestDto) {
        Optional<Doctor> doctorById = doctorRepository.findDoctorById(requestDto.getId());
        if (doctorById.isPresent()) {
            Doctor doctor = doctorById.get();
            doctor.setAbout(requestDto.getAbout());
            doctorRepository.save(doctor);
        }
    }

    @GetMapping("loadDoctorProfile")
    public ResponseEntity<DocDto> loadDoctorProfile() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Doctor> doctorByUserId = doctorRepository.findDoctorByUserId(userService.findByPhone(auth.getName()).getId());
        if (doctorByUserId.isPresent()) {
            Doctor doctor = doctorByUserId.get();
            DocDto docDto = DocDto.fromDoctor(doctor);
            return ResponseEntity.ok(docDto);
        }
        throw new RuntimeException("doctor not found");
    }

    @GetMapping("deleteAppointments/{id}")
    public void deleteAppointments(@PathVariable(name = "id") Long id) {
        appointmentService.delete(id);
        log.error("appointment with ID" + id + " was deleted");
    }

    @PostMapping(value = "treatment")
    public void treatment(@RequestBody TreatmentDto requestDto) {
        treatmentInformationService.editTreatmentInformation(requestDto.getClientID(),requestDto.getDiagnosis(), requestDto.getRecommendations(), requestDto.getSymptoms(), requestDto.getTreatment(),requestDto.getDoctorID());
    }
}
