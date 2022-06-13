package com.example.crmbtf.repository;


import com.example.crmbtf.model.TelegramUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramUsersRepository extends JpaRepository<TelegramUsers, Long> {
    TelegramUsers findDataUserByChatId(Long id);
    Optional<TelegramUsers> findDataUserTgByChatId(Long id);


}
