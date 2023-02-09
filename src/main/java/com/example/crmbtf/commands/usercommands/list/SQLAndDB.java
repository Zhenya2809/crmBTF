package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SQLAndDB implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое  DDL? Какие операции в него входят? Рассказать про них.",
                "Что такое  DML? Какие операции в него входят? Рассказать про них.",
                "Что такое  TCL? Какие операции в него входят? Рассказать про них.",
                "Что такое  DCL? Какие операции в него входят? Рассказать про них.",
                "Нюансы работы с NULL в SQL. Как проверить поле на NULL?",
                "Виды Join’ов?",
                "Что лучше использовать join или подзапросы? Почему?",
                "Что делает UNION?",
                "Чем WHERE отличается от HAVING ( ответа про то что используются в разных частях запроса - недостаточно)?",
                "Что такое ORDER BY?",
                "Что такое GROUP BY?",
                "Что такое DISTINCT?",
                "Что такое LIMIT?",
                "Что такое EXISTS?",
                "Расскажите про операторы IN, BETWEEN, LIKE.",
                "Что делает оператор MERGE? Какие у него есть ограничения?",
                "Какие агрегатные функции вы знаете?",
                "Что такое ограничения (constraints)? Какие вы знаете?",
                "(?) Что такое суррогатные ключи?",
                "Что такое индексы? Какие они бывают?",
                "Чем TRUNCATE отличается от DELETE?",
                "Что такое хранимые процедуры? Для чего они нужны?",
                "Что такое представления (VIEW)? Для чего они нужны?",
                "Что такое временные таблицы? Для чего они нужны?",
                "Что такое транзакции? Расскажите про принципы ACID.",
                "Расскажите про уровни изолированности транзакций.",
                "Что такое нормализация и денормализация? Расскажите про 3 нормальные формы?",
                "Что такое TIMESTAMP?",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("SQL и базы данных");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.SQLAndDB;
    }
}