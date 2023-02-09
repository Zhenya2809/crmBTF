package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Three implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Требуется другая квалификация\n" +
                "Резко увеличивается время на анализ и проектирование систем\n" +
                "Увеличение времени выполнения\n" +
                "Размер кода увеличивается\n" +
                "Неэффективно с точки зрения памяти (мертвый код - тот, который не используется)\n" +
                "Сложность распределения работ на начальном этапе\n" +
                "Себестоимость больше\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Какие недостатки у ООП?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.THREE;
    }
}
