package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainIdea implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Кроссплатформенность языка. В основу Java положен принцип WORA («Write Once Run Anywhere», «Написано один раз, работает везде»).\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Какая основная идея языка?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.MAINIDEA;
    }
}