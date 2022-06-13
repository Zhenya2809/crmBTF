package com.example.crmbtf.commands.registration;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class Authorization implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        try {
            String localState = executionContext.getLocalState();
            TelegramUsers authorizationUser = executionContext.getAuthorizationUser();

            if ((authorizationUser.getEmail() != null) && (authorizationUser.getPhone() != null) && (localState == null)) {
                executionContext.setLocalState("authorized");
            }
            if ((localState == null) && (authorizationUser.getEmail() == null) && (authorizationUser.getPhone() == null)) {
                executionContext.setLocalState("start_registration");
            }
            String step = executionContext.getLocalState();
            Map<String, Registration> authorizationMap = new HashMap<>();
            authorizationMap.put("start_registration", new StartRegistration());
            authorizationMap.put("get_email_and_phone_registration", new GetEmailRegistration());
            authorizationMap.put("authorized", new Authorized());
            Registration registration = authorizationMap.get(step);
            if (registration == null) {
                throw new RuntimeException("fail to find by step " + step);
            }
            registration.execute(executionContext);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Начнем \uD83D\uDE09")||(text.equals("Главное меню"));
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.START_BOT_CHATTING;
    }
}