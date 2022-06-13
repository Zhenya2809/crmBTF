package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import com.example.crmbtf.telegram.inline.InlineButton;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowSite implements Command {

    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<InlineButton>  inlineButtons= List.of(new InlineButton("Наш сайт","https://95.216.146.138:8080/"));
        executionContext.buildInlineKeyboard("Перейдите на наш сайт",inlineButtons);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Покажи свой сайт \uD83C\uDF10");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.SHOW_SITE;
    }
}