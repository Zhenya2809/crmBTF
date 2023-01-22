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
public class Generic implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое дженерики?",
                "Для чего нужны дженерики?",
                "Что такое сырые типы (raw type)?",
                "Что такое вайлдкарды?",
                "Расскажите про принцип PECS ",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Дженерики");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.GENERIC;
    }
}