package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Java8 implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Какие нововведения появились в java 8?",
                "Какие новые классы для работы с датами появились в java 8?",
                "Расскажите про класс Optional",
                "Что такое Nashorn?",
                "Что такое jjs?",
                "Какой класс появился в Java 8 для кодирования/декодирования данных?",
                "Как создать Base64 кодировщик и декодировщик?",
                "Какие дополнительные методы для работы с ассоциативными массивами (maps) появились в Java 8?",
                "Что такое LocalDateTime?",
                "Что такое ZonedDateTime? ","Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("JAVA 8");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.JAVA8;
    }
}