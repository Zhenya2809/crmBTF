package com.example.crmbtf.commands;


import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;

public interface Command {

    void doCommand(ExecutionContext context) throws Exception;
    boolean shouldRunOnText(String text);

    TelegramUsers.botstate getGlobalState();
}
