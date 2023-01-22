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
public class Multithreading implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Чем процесс отличается от потока?",
                "Чем Thread отличается от Runnable? Когда нужно использовать Thread, а когда Runnable?",
                "Что такое монитор? Как монитор реализован в java?",
                "Что такое синхронизация? Какие способы синхронизации существуют в java?",
                "Как работают методы wait(), notify() и notifyAll()?",
                "В каких состояниях может находиться поток?",
                "Что такое семафор? Как он реализован в Java?",
                "Что обозначает ключевое слово volatile? Почему операции над volatile переменными не атомарны?",
                "Для чего нужны Atomic типы данных? Чем отличаются от volatile?",
                "Что такое потоки демоны? Для чего они нужны? Как создать поток-демон?",
                "Что такое приоритет потока? На что он влияет? Какой приоритет у потоков по умолчанию?",
                "Как работает Thread.join()? Для чего он нужен?",
                "Чем отличаются методы yield () и sleep()?",
                "Как правильно остановить поток? Для чего нужны методы .stop(), .interrupt(), .interrupted(), .isInterrupted().",
                "Чем Runnable отличается от Callable?",
                "Что такое FutureTask?",
                "Что такое deadlock?",
                "Что такое livelock?",
                "Что такое race condition?",
                "Что такое Фреймворк fork/join? Для чего он нужен?",
                "Что означает ключевое слово synchronized? Где и для чего может использоваться?",
                "Что является монитором у статического синхронизированного класса?",
                "Что является монитором у нестатического синхронизированного класса?",
                "util. Concurrent поверхностно. ",
                "Stream API & ForkJoinPool Как связаны, что это такое.",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Многопточность");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.MULTITHREADING;
    }
}