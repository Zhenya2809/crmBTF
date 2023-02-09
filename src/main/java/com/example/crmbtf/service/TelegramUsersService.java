package com.example.crmbtf.service;

import com.example.crmbtf.model.TelegramUser;

import java.util.List;
import java.util.Optional;


public interface TelegramUsersService {
    Optional<TelegramUser> findDataUserByChatId(Long id);
    Long getDoctorId(Long id);
    void createUser(Long chatId, String firstName, String lastName, String role);
    void save(TelegramUser user);
    List<Long> findAll();

}
