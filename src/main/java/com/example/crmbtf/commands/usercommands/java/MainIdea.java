package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.Questionnaire;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.MAINIDEA;
    }
}