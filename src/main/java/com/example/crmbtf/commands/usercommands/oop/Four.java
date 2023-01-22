package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Four implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Абстракция, Инкапсуляция, Наследование, Полиморфизм\n" +
                " \n" +
                "Они реализуют IS-A отношения\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Назовите основные принципы ООП");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.FOUR;
    }
}
