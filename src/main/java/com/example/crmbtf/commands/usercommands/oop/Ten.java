package com.example.crmbtf.commands.usercommands.oop;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Ten implements Command {
    @Override
    public void doCommand(ExecutionContext context) throws Exception {

        context.replyMessage("Вот что входит в принципы SOLID:\n" +
                "Single Responsibility Principle (Принцип единственной ответственности).\n" +
                "Open Closed Principle (Принцип открытости/закрытости).\n" +
                "Liskov’s Substitution Principle (Принцип подстановки Барбары Лисков).\n" +
                "Interface Segregation Principle (Принцип разделения интерфейса).\n" +
                "Dependency Inversion Principle (Принцип инверсии зависимостей).\n" +
                " \n" +
                "Принцип единственной ответственности (SRP) гласит: никогда не должно быть больше одной причины изменить класс.\n" +
                " \n" +
                "Принцип открытости/закрытости (OCP) емко описывают так: программные сущности (классы, модули, функции и т.п.) должны быть открыты для расширения, но закрыты для изменения.\n" +
                " \n" +
                "Принцип подстановки Барбары Лисков (LSP) можно описать так: объекты в программе можно заменить их наследниками без изменения свойств программы.\n" +
                " \n" +
                "Принцип разделения интерфейса (ISP) характеризуется следующим утверждением: клиенты не должны быть вынуждены реализовывать методы, которые они не будут использовать.\n" +
                " \n" +
                "Принцип инверсии зависимостей (DIP) описывают так: зависимости внутри системы строятся на основе абстракций.\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("SOLID");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.TEN;
    }
}
