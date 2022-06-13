package com.example.crmbtf.commands.registration.rolemenu;


import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;

import java.util.List;

public class User implements ChoseRole {
    @Override
    public void execute(ExecutionContext executionContext) {
        List<ReplyButton> replyButtonList = List.of(new ReplyButton("О нас"),
                new ReplyButton("Специалисты"),
                new ReplyButton("Заказать обратный звонок"),
                new ReplyButton("Услуги"),
                new ReplyButton("Наш адрес"));
        executionContext.buildReplyKeyboard("Нам есть о чем поговорить! \n" +
                "так хочеться рассказать тебе о нас", replyButtonList);
    }
}
