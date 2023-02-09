package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.repository.TelegramUsersRepository;
import com.example.crmbtf.service.TelegramUsersService;
import lombok.extern.slf4j.Slf4j;
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
    public Optional<TelegramUser> findDataUserByChatId(Long id) {
        return Optional.ofNullable(telegramUsersRepository.findDataUserByChatId(id));
    }

    @Override
    public Long getDoctorId(Long id) {
        TelegramUser dataUserByChatId = telegramUsersRepository.findDataUserByChatId(id);
        return dataUserByChatId.getChatId();
    }

    @Override
    public void createUser(Long chatId, String firstName, String lastName, String role) {
        TelegramUser user = new TelegramUser();
        user.setChatId(chatId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setGlobalState(TelegramUser.botstate.START);
        user.setLocaleState("Main_menu");
        telegramUsersRepository.save(user);
        log.info("user created : chatId=" + chatId + " firstName=" + firstName + " lastName=" + lastName);
    }

    @Override
    public void save(TelegramUser user) {
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
