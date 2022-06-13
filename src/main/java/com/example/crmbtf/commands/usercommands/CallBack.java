package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CallBack implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {
        executionContext.replyMessage("В скором времени наш менеджер свяжеться с Вами");
        Optional<TelegramUsers> dataUserByChatId = executionContext.getTelegramUsersService().findDataUserByChatId(executionContext.getChatId());
        if(dataUserByChatId.isPresent()){
            String phone = dataUserByChatId.get().getPhone();
            Long administratorId = executionContext.getInfoDataService().getAdministratorId();
            executionContext.sendMessageToUserWithId("Перезвоните мне: "+phone,administratorId +executionContext.getUser().getFirstName()+" "+executionContext.getUser().getLastName());
        }
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Заказать обратный звонок");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.CALL_BACK;
    }
}
