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
public class OOP implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = List.of("Что такое ООП?",
                "Какие преимущества у ООП?",
                "Какие недостатки у ООП?",
                "Назовите основные принципы ООП",
                "Что такое инкапсуляция? (с примером)",
                "Что такое наследование? (с примером)",
                "Что такое полиморфизм? (с примером)",
                "Что такое ассоциация, агрегация и композиция?",
                "Расскажите про раннее и позднее связывание.",
                "SOLID","Назад"
        );



        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос из ООП", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("ООП");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.OOP;
    }
}