package com.example.crmbtf.rest;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.Role;
import com.example.crmbtf.model.dto.AdminUserDto;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.DoctorDto;
import com.example.crmbtf.model.dto.PatientDTO;
import com.example.crmbtf.model.dto.UserDto;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.service.DoctorService;
import com.example.crmbtf.service.PatientService;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @Autowired
    public AdminRestControllerV1(UserService userService, DoctorService doctorService, AppointmentService appointmentService, PatientService patientService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
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

    @GetMapping(value = "myProfile")
    public ResponseEntity myProfile() {

        List<PatientDTO> patientDTOList = new ArrayList<>();
        List<Patient> patients = patientService.findAll();
        for (Patient patient : patients) {
            PatientDTO patientDTO = PatientDTO.fromPatient(patient);
            patientDTOList.add(patientDTO);
        }
        return ResponseEntity.ok(patientDTOList);
    }
    @GetMapping(value = "users/search")
    public ResponseEntity searchAllUser() {
//HashMap<Long,User> userHashMap = new HashMap<>();
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userService.findAll();
        for (User user : userList) {
//            userHashMap.put(user.getId(),user);
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setUsername(user.getUsername());
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDtoList.add(userDto);
        }
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping(value = "doctors/search")
    public ResponseEntity searchAllDoctors() {
//HashMap<Long,User> userHashMap = new HashMap<>();
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        List<Doctor> doctors = doctorService.findAll();

        for (Doctor doctor : doctors) {
            DoctorDto doctorDto = DoctorDto.fromDoctor(doctor);
            doctorDtoList.add(doctorDto);

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

    @GetMapping(value = "doctors/delete/{id}")
    public void deleteDoctorsById(@PathVariable(name = "id") Long id) {
        Optional<Doctor> doctorById = doctorService.getDoctorById(id);
        if (doctorById.isPresent()) {
            doctorService.deleteDoctor(id);
        }
    }

    @PostMapping("saveDoctor")
    public ResponseEntity saveDoctor(@RequestBody DoctorDto requestDto) {
        String s = doctorService.createDoctor(requestDto.getDoctorFIO(), requestDto.getSpeciality(), requestDto.getAbout(), requestDto.getLinkPhoto());
        Map<Object, Object> response = new HashMap<>();
        response.put("result", s);
        return ResponseEntity.ok(response);

    }



    @PostMapping("users")
    public ResponseEntity postAppointment(@RequestBody Long id) {

        List<User> userList = new ArrayList<>();
        Iterator<User> getAllUsers = userService.findAll().stream().iterator();
        if (getAllUsers.hasNext()) {
            userList.add(getAllUsers.next());
        }


        return ResponseEntity.ok(userList);

    }


}