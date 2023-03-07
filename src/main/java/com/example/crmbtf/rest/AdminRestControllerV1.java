package com.example.crmbtf.rest;

import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.AdminUserDto;
import com.example.crmbtf.model.dto.DoctorDto;
import com.example.crmbtf.model.dto.PatientDTO;
import com.example.crmbtf.model.dto.UserDto;
import com.example.crmbtf.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final PatientCardService patientCardService;

    @Autowired
    public AdminRestControllerV1(UserService userService, DoctorService doctorService, AppointmentService appointmentService, PatientService patientService, PatientCardService patientCardService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.patientCardService = patientCardService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "users/search")
    public ResponseEntity<List<UserDto>> searchAllUser() {
        List<UserDto> userDtoList = userService.findAll().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping(value = "doctors/search")
    public ResponseEntity<List<DoctorDto>> searchAllDoctors() {
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        List<Doctor> doctors = doctorService.findAll();

        for (Doctor doctor : doctors) {
            if (doctor != null) {
                DoctorDto doctorDto = DoctorDto.fromDoctor(doctor);
                doctorDtoList.add(doctorDto);

            }
        }
        return ResponseEntity.ok(doctorDtoList);
    }

    @GetMapping(value = "sendEmailReminder")
    public void sendEmailReminder() {
        appointmentService.sendEmailReminder();
        log.info("email sent");
    }

    @GetMapping(value = "users/delete/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id) {
        User byId = userService.findById(id);
        List<Role> roles = byId.getRoles();
        List<Role> roleList = List.of(new Role(2L, "ROLE_ADMIN"));
        if (!roleList.equals(roles)) {
            userService.delete(id);
        }
    }

    @GetMapping(value = "patientCard/delete/{id}")
    public void deletePatientCardById(@PathVariable(name = "id") Long id) {
        patientCardService.delete(id);
    }

    @DeleteMapping(value = "doctors/delete/{id}")
    public ResponseEntity<Void> deleteDoctorsById(@PathVariable(name = "id") Long id) {
        try {
            doctorService.deleteDoctor(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("saveDoctor")
    public ResponseEntity<Map<Object, Object>> saveDoctor(@RequestBody DoctorDto requestDto) {
        userService.doctorRegistration(requestDto.getLogin(), requestDto.getPassword(), requestDto.getRePassword(), requestDto.getDoctorFirstName(), requestDto.getDoctorLastName(), requestDto.getEmail(), 3L, "ROLE_DOCTOR");
        User user = userService.findByPhone(requestDto.getLogin());
        String s = doctorService.createDoctor(requestDto.getDoctorFirstName(), requestDto.getDoctorLastName(), requestDto.getSpeciality(), requestDto.getAbout(), requestDto.getLinkPhoto(), user);

        Map<Object, Object> response = new HashMap<>();
        response.put("result", s);
        return ResponseEntity.ok(response);

    }


    @PostMapping("users")
    public ResponseEntity<List<User>> postAppointment(@RequestBody Long id) {

        List<User> userList = new ArrayList<>();
        Iterator<User> getAllUsers = userService.findAll().stream().iterator();
        if (getAllUsers.hasNext()) {
            userList.add(getAllUsers.next());
        }


        return ResponseEntity.ok(userList);

    }

    @GetMapping("searchPatient/{fio}")
    public ResponseEntity<List<PatientDTO>> search(@PathVariable(name = "fio") String fio) {
        List<PatientDTO> patientsDTO = new ArrayList<>();

        List<Patient> patients;
        if (Objects.equals(fio, "null")) {
            patients = patientService.findAll();

        } else {
            patients = patientService.searchPatientsByName(fio);
        }

        for (Patient patient : patients) {
            PatientDTO patientDTO = PatientDTO.fromPatient(patient);
            patientsDTO.add(patientDTO);
        }

        return ResponseEntity.ok(patientsDTO);
    }


}