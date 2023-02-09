
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CrossPlatform implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Кроссплатформенность достигается за счет использования виртуальной машины Java (Java Virtual Machine – JVM).\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("За счет чего обеспечивается кроссплатформенность?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.KROSSPLATFORM;
    }
}