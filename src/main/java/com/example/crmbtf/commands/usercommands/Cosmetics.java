package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cosmetics implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext)  {


        List<ReplyButton> replyButtonList = List.of(new ReplyButton("текст1"),new ReplyButton("Главное меню"));
        executionContext.buildReplyKeyboard("TextToKeyboard", replyButtonList);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Косметика");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.COSMETICS;
    }
}
