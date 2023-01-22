package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.repository.TelegramUsersRepository;
import com.example.crmbtf.service.TelegramUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TelegramUserServiceImpl implements TelegramUsersService {
    private final TelegramUsersRepository telegramUsersRepository;


    public TelegramUserServiceImpl(TelegramUsersRepository telegramUsersRepository) {
        this.telegramUsersRepository = telegramUsersRepository;
    }

    @Override
    public Optional<TelegramUsers> findDataUserByChatId(Long id) {
        return Optional.ofNullable(telegramUsersRepository.findDataUserByChatId(id));
    }

    @Override
    public Long getDoctorId(Long id) {
        TelegramUsers dataUserByChatId = telegramUsersRepository.findDataUserByChatId(id);
        return dataUserByChatId.getDoctorId();
    }

    @Override
    public void createUser(Long chatId, String firstName, String lastName, String role) {
        TelegramUsers user = new TelegramUsers();
        user.setChatId(chatId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setGlobalState(TelegramUsers.botstate.START);
        user.setLocaleState("Main_menu");
        telegramUsersRepository.save(user);
        log.info("user created : chatId=" + chatId + " firstName=" + firstName + " lastName=" + lastName);
    }

    @Override
    public void save(TelegramUsers user) {
        telegramUsersRepository.save(user);
    }

    @Override
    public List<Long> findAll() {
        List<Long> chatIdList = new ArrayList<>();
        telegramUsersRepository.findAll().forEach(e -> {
            Long chatId = e.getChatId();
            chatIdList.add(chatId);
        });
        return chatIdList;
    }
}
