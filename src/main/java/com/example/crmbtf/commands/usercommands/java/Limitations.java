
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Limitations implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Одним из основных минусов языка является его большой объем потребления памяти даже, когда не проводятся сложные операции.\n" +
                " \n" +
                "Низкая скорость и безопасность. Все языки с высоким уровнем страдают малой производительностью, этому способствуют различные функции – очистка памяти, настройки, блокировки.\n" +
                " \n" +
                "Многословность и сложность кода. Язык с длинными, трудными предложениями помогает при его изучении. Но лишняя информация затрудняет чтение. Поэтому в среде программистов Java считается слишком громоздким.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Какие недостатки у Java?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.LIMITATIONS;
    }
}