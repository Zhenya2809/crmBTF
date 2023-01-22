
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ClassLoader implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("java.lang.ClassLoader - это абстрактный класс. Является частью JRE (Java Runtime Environment), которая динамически загружает классы Java в JVM (виртуальная машина Java).\n" +
                "Обычно классы загружаются только по запросу. Система исполнения в Java не должна знать о файлах и файловых системах благодаря загрузчику классов. Делегирование является важной концепцией, которую выполняет загрузчик. Загрузчик классов отвечает за поиск библиотек, чтение их содержимого и загрузку классов, содержащихся в библиотеках. Эта загрузка обычно выполняется «по требованию», поскольку она не происходит до тех пор, пока программа не вызовет класс. Класс с именем может быть загружен только один раз данным загрузчиком классов.\n" +
                " \n" +
                "При запуске JVM, используются три загрузчика классов:\n" +
                "Bootstrap class loader (Загрузчик класса Bootstrap)\n" +
                "Extensions class loader (Загрузчик класса расширений)\n" +
                "System class loader (Системный загрузчик классов)\n" +
                " \n" +
                "https://github.com/enhorse/java-interview/blob/master/jvm.md#classloader \n" +
                "http://java-online.ru/java-classloader.xhtml\n" +
                "https://habr.com/ru/post/103830/\n" +
                "https://javarush.ru/groups/posts/646-kak-proiskhodit-zagruzka-klassov-v-jvm\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое загрузчик классов (classloader)?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.CLASSLOADER;
    }
}