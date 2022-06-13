package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Beauticians implements Command {

    @Override
    public void doCommand(ExecutionContext executionContext) {


        List<ReplyButton> replyButtonList = List.of(
                new ReplyButton("Записаться к косметологу"),
                new ReplyButton("Специалисты"),
                new ReplyButton("Услуги"),
                new ReplyButton("Наш адрес"),
                new ReplyButton("Главное меню"));
        executionContext.buildReplyKeyboard("Вы можете задать все вопросы нашему администратору", replyButtonList);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Косметологи");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.BEAUTICIANS;
    }
}