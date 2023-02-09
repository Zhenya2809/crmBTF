package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Exception implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое исключения?",
                "Опишите иерархию исключений.",
                "Расскажите про обрабатываемые и необрабатываемые исключения",
                "Можно ли обработать необрабатываемые исключения?",
                "Какой оператор позволяет принудительно выбросить исключение?",
                "О чем говорит ключевое слово throws?",
                "Как создать собственное («пользовательское») исключение?",
                "Расскажите про механизм обработки исключений в java (Try-catch-finally)",
                "Возможно ли использование блока try-finally (без catch)?",
                "Может ли один блок catch отлавливать сразу несколько исключений?",
                "Всегда ли выполняется блок finally? Существуют ли ситуации, когда блок finally не будет выполнен?",
                "Может ли метод main() выбросить исключение во вне и если да, то где будет происходить обработка данного исключения?",
                "В каком порядке следует обрабатывать исключения в catch блоках?",
                "Что такое механизм try-with-resources?",
                "Что произойдет если исключение будет выброшено из блока catch после чего другое исключение будет выброшено из блока finally?",
                "Что произойдет если исключение будет выброшено из блока catch после чего другое исключение будет выброшено из метода close() при использовании try-with-resources?", "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Исключения");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.EXCEPTION;
    }
}