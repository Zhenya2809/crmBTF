package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.*;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.repository.RoleRepository;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DoctorRepository doctorRepository;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+~`|}{[]:;?><,./-=";
    private static final String ALL_CHARACTERS = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + NUMBERS + SPECIAL_CHARACTERS;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.doctorRepository = doctorRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    public List<String> freeTimeToAppointmentForDay(LocalDate day, Long docId, HashMap<Date, List<String>> dateAndTimeMap) {
        List<String> timeList = new ArrayList<>();
        timeList.add("08:00");
        timeList.add("09:00");
        timeList.add("10:00");
        timeList.add("11:00");
        timeList.add("12:00");
        timeList.add("13:00");
        timeList.add("14:00");
        timeList.add("15:00");
        timeList.add("16:00");
        timeList.add("17:00");
        timeList.add("18:00");
        timeList.add("19:00");

        LocalDate today = LocalDate.now();
        ArrayList<DaySchedule> daySchedules = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LocalDate localDate = today.plusDays(i);
            DaySchedule daySchedule = new DaySchedule();
            String key = localDate.toString();
            daySchedule.setDate(key);
            if (dateAndTimeMap.get(java.sql.Date.valueOf(key)) != null) {
                daySchedule.setAvailable(new HashSet<>(dateAndTimeMap.get(java.sql.Date.valueOf(key))));
            } else {
                daySchedule.setAvailable(new HashSet<>());
            }
            daySchedules.add(daySchedule);

        }
        List<String> listToday = new ArrayList<>();

        daySchedules.forEach(e -> {
            String date = e.getDate();
            HashSet<String> available = e.getAvailable();
            if (date.equals(day.toString())) {
                listToday.addAll(available);
            }
        });
        List<String> list = listToday.stream().map(e -> {
            String[] split = e.split(":");
            return split[0] + ":" + split[1];
        }).toList();

        timeList.removeAll(list);
        return timeList;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByPhone(String phone) {
        Optional<User> result = userRepository.findByPhone(phone);
        if (result.isPresent()) {
            log.info("IN findByUsername - user: {} found by username: {}", result, phone);
            return result.get();
        } else
            throw new RuntimeException("user not found");
    }
    @Override
    public String randomPassword() {
        int passwordLength = 10;
        return generateRandomPassword(passwordLength);
    }

    public static String generateRandomPassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String userRegistration(String phone, String password, String rePassword, String firstName, String lastName, String email) {

        Optional<User> result = userRepository.findByPhone(phone);
        if (result.isPresent()) {
            log.error("A user with the same name already exists");
            return "A user with the same name already exists";

        }
        if (!password.equals(rePassword)) {
            log.info("Passwords do not match");
            return "Passwords do not match";
        }
        Date date = new Date();
        User user = new User();
        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreated(date);
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
        log.info("IN userRegistration = user with phone: {} successfully registred", phone);
        return "IN userRegistration = user with phone: { " + phone + " } successfully registred";
    }

    @Override
    public String doctorRegistration(String phone, String password, String rePassword, String firstName, String lastName, String email, Long id, String role) {


        Optional<User> result = userRepository.findByPhone(phone);
        if (result.isPresent()) {
            log.error("A user with the same name already exists");
            return "A user with the same name already exists";

        }
        if (!password.equals(rePassword)) {
            log.info("Passwords do not match");
            return "Passwords do not match";
        }
        Date date = new Date();
        User user = new User();
        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreated(date);
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of(new Role(3L, "ROLE_DOCTOR")));
        userRepository.save(user);
        log.info("IN userRegistration = user with phone: {} successfully registred", phone);
        return "IN userRegistration = user with phone: { " + phone + " } successfully registred";
    }
}