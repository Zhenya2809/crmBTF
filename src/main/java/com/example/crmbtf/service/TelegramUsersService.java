package com.example.crmbtf.service;

import com.example.crmbtf.model.TelegramUsers;

import java.util.List;
import java.util.Optional;


public interface TelegramUsersService {
    Optional<TelegramUsers> findDataUserByChatId(Long id);
    Long getDoctorId(Long id);
    void createUser(Long chatId, String firstName, String lastName, String role);
    void save(TelegramUsers user);
    List<Long> findAll();
}
