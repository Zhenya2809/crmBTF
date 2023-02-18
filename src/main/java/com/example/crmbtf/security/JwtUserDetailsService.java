package com.example.crmbtf.security;

import com.example.crmbtf.model.User;
import com.example.crmbtf.security.jwt.JwtUser;
import com.example.crmbtf.security.jwt.JwtUserFactory;
import com.example.crmbtf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.findByPhone(phone);

        if (user == null) {
            log.error("User with phone: " + phone + " not found");
            throw new UsernameNotFoundException("User with phone: " + phone + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with phone: {} successfully loaded", phone);
        return jwtUser;
    }
}