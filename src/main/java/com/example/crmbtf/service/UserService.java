package com.example.crmbtf.service;

import com.example.crmbtf.model.User;

import java.util.List;



public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    String userRegistration(String username,String password, String rePassword,String firsName,String lastName,String email);
}