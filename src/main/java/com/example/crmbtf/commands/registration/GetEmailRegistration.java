package com.example.crmbtf.commands.registration;


import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;

public class GetEmailRegistration implements Registration {
    @Override
    public void execute(ExecutionContext executionContext) {
        String inputMessage = executionContext.getUpdate().getMessage().getText();

        TelegramUsers user = executionContext.getAuthorizationUser();
        if (inputMessage.contains("@")) {
            user.setEmail(inputMessage);
            executionContext.getTelegramUsersService().save(user);
            executionContext.getContactKeyboard();
            executionContext.setLocalState("authorized");
        } else {
            executionContext.replyMessage("ввід помилковий, спробуйте ще раз");
        }

    }
}
