package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Address implements Command {
    @Autowired
    MainMenu menu;

    @Override
    public void doCommand(ExecutionContext executionContext) throws TelegramApiException {


        executionContext.replyMessage("Найти нас очень просто!\n" +
                "Киев, ул. Вацлава Гавела 9а \n" +
                "или позвонить нам " +
                "\uD83D\uDCDE +38(077)-777-77-77");
        executionContext.sendAddress(30.425590655889714, 50.44593677173056);

        menu.doCommand(executionContext);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Наш адрес");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.ADDRESS;
    }
}
