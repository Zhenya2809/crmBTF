package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Specialists implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<ReplyButton> replyButtonList = List.of(new ReplyButton("Доктора"),
                new ReplyButton("і тут ще хтось"),
                new ReplyButton("Главное меню"));

        executionContext.buildReplyKeyboard(executionContext.getFirstName() + ", рада представить тебе нашу команду профессионалов. \n" +
                "Качественный подбор персонала, помог собрать лучших врачей - специалистов.", replyButtonList);


        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Специалисты");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.SPECIALISTS;
    }
}