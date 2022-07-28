package com.example.crmbtf.rest;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.dto.AdminUserDto;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.RegistrationRequestDto;
import com.example.crmbtf.model.dto.UserDto;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;


    @Autowired
    public AdminRestControllerV1(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
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
    public ResponseEntity getAppointment() {
//HashMap<Long,User> userHashMap = new HashMap<>();
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userService.findAll().stream().toList();
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

    @GetMapping(value = "users/delete/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id) {
        userService.delete(id);
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