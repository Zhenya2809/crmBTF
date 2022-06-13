package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.service.DoctorService;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Doctors implements Command {
    @Autowired
    public DoctorService doctorService;

    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<ReplyButton> replyButtonList = List.of(new ReplyButton("Записаться к доктору"),
                new ReplyButton("Специалисты"),
                new ReplyButton("Услуги"),
                new ReplyButton("Наш адрес"),
                new ReplyButton("Главное меню"));
        executionContext.buildReplyKeyboard("Хотите записаться на прием?", replyButtonList);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Доктора");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.DOCTORS;
    }
}