package com.example.crmbtf.commands.usercommands;
import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Back implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {


        List<String> list = List.of("ООП",
                "JAVA",
                "Процедурная Java",
                "ООП в Java",
                "Исключения",
                "Сериализация и копирование",
                "Дженерики",
                "Коллекции",
                "Функциональные интерфейсы",
                "Stream API",
                "JAVA 8",
                "Многопточность",
                "SQL и базы данных",
                "JPA и Hibernate",
                "SPRING",
                "Паттерны",
                "Алгоритмы и структуры данных"

        );
        executionContext.buildReplyKeyboardWithStringList("Выберите тему", list);
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Назад");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.TEST;
    }
}