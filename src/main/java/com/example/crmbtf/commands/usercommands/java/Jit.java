
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Jit implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("JIT-компиляция (Just-in-time compilation, компиляция «на лету») – технология увеличения производительности программных систем, использующих байт-код, путём компиляции байт-кода в машинный код или в другой формат непосредственно во время работы программы.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое JIT?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.JIT;
    }
}