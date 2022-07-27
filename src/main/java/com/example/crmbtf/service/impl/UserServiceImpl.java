package com.example.crmbtf.service.impl;

import com.example.crmbtf.model.Role;
import com.example.crmbtf.model.Status;
import com.example.crmbtf.model.User;
import com.example.crmbtf.repository.RoleRepository;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isPresent()) {
            log.info("IN findByUsername - user: {} found by username: {}", result, username);
            return result.get();
        } else
            throw new RuntimeException("user not found");
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
    public String userRegistration(String username, String password, String rePassword, String firstName, String lastName, String email) {


        Optional<User> result = userRepository.findByUsername(username);
        if(result.isPresent()){
            log.error("A user with the same name already exists");
            return "A user with the same name already exists";
        }
        if (!password.equals(rePassword)) {
            log.info("Passwords do not match");
            return "Passwords do not match";
        }
        Role role = new Role();
        Date date = new Date();
        role.setName("ROLE_USER");
        role.setCreated(date);
        role.setStatus(Status.ACTIVE);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        User user = new User();
        user.setUsername(username);
        user.setRoles(roleList);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreated(date);
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        log.info("IN userRegistration = user with username: {} successfully registred", username);
        return "User with " +username+" successfully registered";
    }

}