
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JVM implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Java Virtual Machine – виртуальная машина Java – основная часть исполняющей системы Java, так называемой Java Runtime Environment (JRE). Виртуальная машина Java исполняет байт-код Java, предварительно созданный из исходного текста Java-программы компилятором Java (javac).\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое JVM?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.JVM;
    }
}