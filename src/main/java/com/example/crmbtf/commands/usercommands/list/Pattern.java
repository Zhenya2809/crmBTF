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
public class Pattern implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое «шаблон проектирования»?",
                "Назовите основные характеристики шаблонов",
                "Назовите три основные группы паттернов",
                "Расскажите про паттерн Одиночка (Singleton)",
                "Расскажите про паттерн Строитель (Builder)",
                "Расскажите про паттерн Фабричный метод (Factory Method)",
                "Расскажите про паттерн Абстрактная фабрика (Abstract Factory)",
                "Расскажите про паттерн Прототип (Prototype)",
                "Расскажите про паттерн Адаптер (Adapter)",
                "Расскажите про паттерн Декоратор (Decorator)",
                "Расскажите про паттерн Заместитель (Proxy)",
                "Расскажите про паттерн Итератор (Iterator)",
                "Расскажите про паттерн Шаблонный метод (Template Method)",
                "Расскажите про паттерн Цепочка обязанностей (Chain of Responsibility)",
                "Какие паттерны используются в Spring Framework?",
                "Какие паттерны используются в Hibernate?",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Паттерны");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.PATTERN;
    }
}