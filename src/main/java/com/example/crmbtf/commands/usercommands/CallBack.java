package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CallBack implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("В скором времени наш менеджер свяжеться с Вами");
        Optional<TelegramUser> dataUserByChatId = executionContext.getTelegramUsersService().findDataUserByChatId(executionContext.getChatId());
        if (dataUserByChatId.isPresent()) {
            String phone = dataUserByChatId.get().getPhone();
            Long administratorId = executionContext.getInfoDataService().getAdministratorId();
            executionContext.sendMessageToUserWithId("Перезвоните мне: " + phone, administratorId + executionContext.getUser().getFirstName() + " " + executionContext.getUser().getLastName());
        }
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Заказать обратный звонок");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.CALL_BACK;
    }
}
