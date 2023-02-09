package com.example.crmbtf.repository;


import com.example.crmbtf.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramUsersRepository extends JpaRepository<TelegramUser, Long> {
    TelegramUser findDataUserByChatId(Long id);
    Optional<TelegramUser> findDataUserTgByChatId(Long id);

    Optional<TelegramUser> findTelegramUserByPhone(String phone);


}
