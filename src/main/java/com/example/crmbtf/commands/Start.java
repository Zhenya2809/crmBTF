package com.example.crmbtf.commands;


import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Start implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<ReplyButton> replyButtonList = List.of(new ReplyButton("Начнем \uD83D\uDE09"),
                new ReplyButton("Покажи свой сайт \uD83C\uDF10"),
//                new ReplyButton("Теорка"),
                new ReplyButton("О нас"));

        executionContext.buildReplyKeyboard("Привет " + executionContext.getFirstName() + "\n" +
                "Я виртуальный помощник современного медицинского центра красоты и здоровья CLINIC_NAME\n" +
                "Чем могу вам помочь?", replyButtonList);
        executionContext.setLocalState(null);
    }


    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("/start");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.START;
    }
}