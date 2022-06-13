package com.example.crmbtf.commands.registration.rolemenu;


import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Admin implements ChoseRole {
    @Override
    public void execute(ExecutionContext executionContext) {
        log.info("SEND ROLE MENU - ADMIN");
        List<ReplyButton> replyButtonList = List.of(
                new ReplyButton("Вход"),
                new ReplyButton("Отправить уведомления"));
        executionContext.buildReplyKeyboard("Привет " + executionContext.getLastName() + " \n" +
                "не забудь войти, выйти с акаунта", replyButtonList);

    }
}
