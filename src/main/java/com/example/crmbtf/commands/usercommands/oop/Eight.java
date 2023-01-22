package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Eight implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Реализуют HAS-A (имеет) отношения\n" +
                " \n" +
                "HAS-A отношения основаны на использовании. Выделяют три варианта отношения HAS-A: ассоциация, агрегация и композиция.\n" +
                " \n" +
                "Начнем с ассоциации. В этих отношениях объекты двух классов могут ссылаться друг на друга. Например, класс Horse HAS-A Halter если код в классе Horse содержит ссылку на экземпляр класса Halter.\n" +
                " \n" +
                "Агрегация и композиция являются частными случаями ассоциации. Агрегация - отношение, когда один объект является частью другого. А композиция - еще более тесная связь, когда объект не только является частью другого объекта, но и вообще не может принадлежать другому объекту.\n" +
                " \n" +
                "Агрегация. Объект класса Halter создается извне Horse и передается в конструктор для установления связи. Если объект класса Horse будет удален, объект класса Halter может и дальше использоваться, если, конечно, на него останется ссылка.\n" +
                " \n" +
                "Композиция. Объект класса Halter создается в конструкторе, что означает более тесную связь между объектами. Объект класса Halter не может существовать без создавшего его объекта Horse.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое ассоциация, агрегация и композиция?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.EIGHT;
    }
}
