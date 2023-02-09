package com.example.crmbtf.commands.usercommands.procedureJava;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Procedure1 implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {






    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("SPRING");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.SPRING;
    }
}