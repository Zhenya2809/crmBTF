package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Five implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Инкапсуляция – это свойство системы, позволяющее объединить данные и методы, работающие с ними, в классе и скрыть детали реализации от пользователя.\n" +
                " \n" +
                "Инкапсуляция – это механизм “обёртывания” данных или кода, который работает с этими данными в отдельный модуль. Инкапсулированные, таким образом, переменные, отделены от других классов и доступ к ним возможен только с помощью методов класса, который содержит эти переменные.\n" +
                " \n" +
                "Пример: паровые машины управлялись тяжело. Нужно было управлять двумя колесами раздельно, подсыпать уголь, следить за температурой воды. В современных машинах вся эта функциональность спрятана внутри и нужно только управлять рулем и давить на газ\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое инкапсуляция? (с примером)");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.FIVE;
    }
}
