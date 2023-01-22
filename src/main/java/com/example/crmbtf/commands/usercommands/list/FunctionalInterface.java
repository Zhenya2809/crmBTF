package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.Questionnaire;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FunctionalInterface implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое функциональный интерфейс?",
                "Для чего нужна аннотация @FunctionalInterface?",
                "Какие встроенные функциональные интерфейсы вы знаете?",
                "Что такое ссылка на метод?",
                "Что такое лямбда-выражение? Чем его можно заменить?", "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Функциональные интерфейсы");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.FUNCTIONAL_INTERFACE;
    }
}