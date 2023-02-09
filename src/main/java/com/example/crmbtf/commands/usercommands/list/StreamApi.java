package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StreamApi implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое Stream API? Для чего нужны стримы?",
                "Почему Stream называют ленивым?",
                "Какие существуют способы создания стрима?",
                "Как из коллекции создать стрим?",
                "Какие промежуточные методы в стримах вы знаете?",
                "Расскажите про метод peak().",
                "Расскажите про метод map().",
                "Расскажите про метод flatMap().",
                "Чем отличаются методы map() и flatMap().",
                "Расскажите про метод filter()",
                "Расскажите про метод limit()",
                "Расскажите про метод skip()",
                "Расскажите про метод sorted()",
                "Расскажите про метод distinct()",
                "Какие терминальные методы в стримах вы знаете?",
                "Расскажите про метод collect()",
                "Расскажите про метод reduce()",
                "Расскажите про класс Collectors и его методы.",
                "Расскажите о параллельной обработке в Java 8.",
                "Что такое IntStream и DoubleStream?",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос из JAVA", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Stream API");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.STREAM_API;
    }
}