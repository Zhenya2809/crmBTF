package com.example.crmbtf.commands.registration.rolemenu;


import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Doctor implements ChoseRole {
    @Override
    public void execute(ExecutionContext executionContext) {
        log.info("SEND ROLE MENU - DOCTOR");
        List<ReplyButton> replyButtonList = List.of(new ReplyButton("Записи на сегодня"),
                new ReplyButton("Мои приемы"));
        executionContext.buildReplyKeyboard("Доброе утро доктор " + executionContext.getLastName() + " \n" +
                "так хочеться рассказать тебе о нас", replyButtonList);
    }
}
