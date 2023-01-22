package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class One implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Объектно-ориентированное программирование (ООП) — методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определённого класса, а классы образуют иерархию наследования.");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое ООП?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.ONE;
    }
}
