package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Two implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Возможность легкой модификации (при грамотном анализе и проектировании)\n" +
                "Возможность отката при наличии версий\n" +
                "Более легкая расширяемость\n" +
                "«Более естественная» декомпозиция программного обеспечения, которая существенно облегчает его разработку.\n" +
                "Сокращение количества межмодульных вызовов и уменьшение объемов информации, передаваемой̆ между модулями.\n" +
                "Увеличивается показатель повторного использования кода.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Какие преимущества у ООП?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.TWO;
    }
}
