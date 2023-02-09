package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class Java implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of("Какая основная идея языка?",
                "За счет чего обеспечивается кроссплатформенность?",
                "Какие преимущества у Java?",
                "Какие недостатки у Java?",
                "Что такое JDK? Что в него входит?",
                "Что такое JVM?",
                "Что такое byte code?",
                "Что такое загрузчик классов (classloader)?",
                "Что такое JIT?",
                "Что такое сборщик мусора? (Garbage collector)",
                "Что такое Heap и Stack память в Java? Чем они отличаются?","Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("JAVA");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.JAVA;
    }
}