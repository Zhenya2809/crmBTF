package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Nine implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Ассоциация вызова метода с телом метода известна как связывание в Java. Есть два вида связывания.\n" +
                " \n" +
                "Фундаментальное различие между статическим и динамическим связыванием в Java состоит в том, что первое происходит рано, во время компиляции на основе типа ссылочной переменной, а второе – позднее, во время выполнения, с использованием конкретных объектов.\n" +
                " \n" +
                "В статическом связывании вызов метода связан с телом метода во время компиляции. Это также известно как раннее связывание. Делается с помощью статических, частных и, окончательных методов.\n" +
                " \n" +
                "В динамическом связывании вызов метода связан с телом метода во время выполнения. Это также известно как позднее связывание. Делается с помощью методов экземпляра.\n" +
                " \n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Расскажите про раннее и позднее связывание.");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.NINE;
    }
}
