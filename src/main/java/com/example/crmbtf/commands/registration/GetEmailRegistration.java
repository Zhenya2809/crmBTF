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
            executionContext.setLocalState("get_email_and_phone_registration");
        } else {
            executionContext.replyMessage("Нажмите кнопку - поделиться номером телефона ⬇️");
        }

    }
}
