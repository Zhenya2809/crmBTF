package com.example.crmbtf.service;

import com.example.crmbtf.model.User;

import java.time.LocalDate;
import java.util.*;


public interface UserService {

    public  List<String> freeTimeToAppointmentForDay(LocalDate day, Long docId, HashMap<Date, List<String>> dateAndTimeMap);
    User register(User user);

    List<User> getAll();

    User findByPhone(String phone);

    User findById(Long id);

    void delete(Long id);

    List<User> findAll();

    String userRegistration(String username,String password, String rePassword,String firsName,String lastName,String email,Long id,String role);
}