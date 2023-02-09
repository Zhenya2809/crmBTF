package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProcedureJava implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Какие примитивные типы данных есть в Java?",
                "Что такое char?",
                "Сколько памяти занимает boolean?",
                "Что такое классы-обертки?",
                "Что такое автоупаковка и автораспаковка?",
                "Что такое явное и неявное приведение типов? В каких случаях в java нужно использовать явное приведение?",
                "Что такое пул интов?",
                "Какие нюансы у строк в Java?",
                "Что такое пул строк?",
                "Почему не рекомендуется изменять строки в цикле? Что рекомендуется использовать?",
                "Почему строки не рекомендуется использовать для хранения паролей? ",
                "Почему String неизменяемый и финализированный класс?",
                "Почему строка является популярным ключом в HashMap в Java? ",
                "Что делает метод intern() в классе String?",
                "Можно ли использовать строки в конструкции switch?",
                "Какая основная разница между String, StringBuffer, StringBuilder?",
                "Существуют ли в java многомерные массивы?",
                "Какими значениями инициируются переменные по умолчанию?",
                "Что такое сигнатура метода?",
                "Расскажите про метод main",
                "Каким образом переменные передаются в методы, по значению или по ссылке? ",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Процедурная Java");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.PROCEDURE_JAVA;
    }
}