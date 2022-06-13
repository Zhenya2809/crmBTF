package com.example.crmbtf.commands.admincommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminLogin implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.getInfoDataService().setAdministratorToday(context.getChatId());
        context.replyMessage("Вход успешный");

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Вход");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.LOGIN;
    }
}
