package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Seven implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Полиморфизм – это свойство системы использовать объекты с одинаковым интерфейсом без информации о типе и внутренней структуре объекта.\n" +
                " \n" +
                "Пример: Любой человек учится водить машину, независимо от того какая эта машина.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое полиморфизм? (с примером)");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.SEVEN;
    }
}
