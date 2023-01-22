package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Six implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Наследование – это свойство системы, позволяющее описать новый класс на основе уже существующего с частично или полностью заимствующейся функциональностью. Класс, от которого производится наследование, называется базовым или родительским. Новый класс – потомком, наследником или производным классом.\n" +
                " \n" +
                "Пример: новые автомобили используют наработки и базу предыдущих\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое наследование? (с примером)");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.SIX;
    }
}
