package com.example.crmbtf.rest;

import com.example.crmbtf.model.Role;
import com.example.crmbtf.model.Status;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.model.dto.AuthenticationRequestDto;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.TelegramUserDto;
import com.example.crmbtf.model.dto.UserDto;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.security.jwt.JwtTokenProvider;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String phone = requestDto.getPhone();
            String password = requestDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password));
            User user = userService.findByPhone(phone);

            if (user == null) {
                log.info("user is null");
                throw new UsernameNotFoundException("User with phone: " + phone + " not found");
            }

            String token = jwtTokenProvider.createToken(phone, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("phone", phone);
            response.put("token", token);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.error(e.toString());
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("loginTelegram/{phone}/{role}")
    public ResponseEntity loginTelegram(@PathVariable(name = "phone") String phone,
                                        @PathVariable(name = "role") String role) {
        try {


            String token = jwtTokenProvider.createToken(phone, role);

            Map<Object, Object> response = new HashMap<>();
            response.put("phone", phone);
            response.put("token", token);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.error(e.toString());
            throw new BadCredentialsException("Invalid phone or password");
        }
    }

    @PostMapping(value = "createacc")
    public ResponseEntity<UserDto> createAccount(@RequestBody TelegramUserDto telegramUser) {
        User user = new User();

        user.setEmail(telegramUser.getEmail());
        user.setRoles(List.of(new Role(1L, "ROLE_USER")));
        user.setPhone(telegramUser.getPhone());
        user.setFirstName(telegramUser.getFirstName());
        user.setLastName(telegramUser.getLastName());
        user.setStatus(Status.ACTIVE);
        UserDto userDto = UserDto.fromUser(user);
        Optional<User> byPhone = userRepository.findByPhone(telegramUser.getPhone());

        if (byPhone.isPresent()) {
            log.info("user is present");
            return ResponseEntity.ok(UserDto.fromUser(byPhone.get()));
        } else {
            userRepository.save(user);
            log.info("account to userPhone: {} created", user.getPhone());
            return ResponseEntity.ok(userDto);
        }
    }
}