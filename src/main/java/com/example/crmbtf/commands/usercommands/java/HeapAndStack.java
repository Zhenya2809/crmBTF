package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeapAndStack implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Heap (куча) используется Java Runtime для выделения памяти под объекты и классы. Создание нового объекта также происходит в куче. Это же является областью работы сборщика мусора. Любой объект, созданный в куче, имеет глобальный доступ и на него могут ссылаться из любой части приложения.\n" +
                "Stack (стек) это область хранения данных также находящееся в общей оперативной памяти (RAM). Всякий раз, когда вызывается метод, в памяти стека создается новый блок, который содержит примитивы и ссылки на другие объекты в методе. Как только метод заканчивает работу, блок также перестает использоваться, тем самым предоставляя доступ для следующего метода. Размер стековой памяти намного меньше объема памяти в куче. Стек в Java работает по схеме LIFO (Последний-вошел-Первый-вышел)\n" +
                "Различия между Heap и Stack памятью:\n" +
                "Куча используется всеми частями приложения в то время как стек используется только одним потоком исполнения программы.\n" +
                "Всякий раз, когда создается объект, он всегда хранится в куче, а в памяти стека содержится лишь ссылка на него. Память стека содержит только локальные переменные примитивных типов и ссылки на объекты в куче.\n" +
                "Объекты в куче доступны с любой точки программы, в то время как стековая память не может быть доступна для других потоков.\n" +
                "Стековая память существует лишь какое-то время работы программы, а память в куче живет с самого начала до конца работы программы.\n" +
                "Если память стека полностью занята, то Java Runtime бросает исключение java.lang.StackOverflowError. Если заполнена память кучи, то бросается исключение java.lang.OutOfMemoryError: Java Heap Space.\n" +
                "Размер памяти стека намного меньше памяти в куче.\n" +
                "Из-за простоты распределения памяти, стековая память работает намного быстрее кучи.\n" +
                " \n" +
                "https://javadevblog.com/chto-takoe-heap-i-stack-pamyat-v-java.html\n" +
                "https://topjava.ru/blog/stack-and-heap-in-java\n" +
                "https://habr.com/ru/post/510454/\n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое Heap и Stack память в Java? Чем они отличаются?");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.HEAPANDSTACK;
    }
}