package com.example.crmbtf.commands;


import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;

public interface Command {

    void doCommand(ExecutionContext context) throws Exception;
    boolean shouldRunOnText(String text);

    TelegramUser.botstate getGlobalState();
}
