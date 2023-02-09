package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Collection implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое «коллекция»?",
                "Расскажите про иерархию коллекций",
                "Почему Map — это не Collection, в то время как List и Set являются Collection?",
                "В чем разница между классами java.util.Collection и java.util.Collections?",
                "Какая разница между итераторами с fail-fast и fail-safe поведением? (С примерами)",
                "Чем различаются Enumeration и Iterator?",
                "Как между собой связаны Iterable, Iterator и «for-each»?",
                "Можно ли итерируясь по ArrayList удалить элемент? Какое вылетит исключение?",
                "Как поведёт себя коллекция, если вызвать iterator.remove()?",
                "Чем Set отличается от List?",
                "Расскажите про интерфейс Set",
                "Расскажите про реализации интерфейса Set",
                "В чем отличия TreeSet и HashSet?",
                "Чем LinkedHashSet отличается от HashSet?",
                "Что будет, если добавлять элементы в TreeSet по возрастанию?",
                "Как устроен HashSet, сложность основных операций.",
                "Как устроен LinkedHashSet, сложность основных операций.",
                "Как устроен TreeSet, сложность основных операций.",
                "Расскажите про интерфейс List",
                "Как устроен ArrayList, сложность основных операций.",
                "Как устроен LinkedList, сложность основных операций.",
                "Почему LinkedList реализует и List, и Deque?",
                "Чем отличаются ArrayList и LinkedList?",
                "Что такое Queue?",
                "Что такое Dequeue? Чем отличается от Queue?",
                "Приведите пример реализации Dequeue.",
                "Какая коллекция реализует  FIFO?",
                "Какая коллекция реализует  LIFO?",
                "Оцените количество памяти на хранение одного примитива типа byte в LinkedList?",
                "Оцените количество памяти на хранение одного примитива типа byte в ArrayList?",
                "Какие существуют реализации Map?",
                "Как устроена HashMap, сложность основных операций? (Расскажите про принцип корзин)",
                "Что такое LinkedHashMap?",
                "Как устроена TreeMap, сложность основных операций?",
                "Что такое WeakHashMap?",
                "Как работает HashMap при попытке сохранить в него два элемента по ключам с одинаковым hashCode(), но для которых equals() == false?",
                "Что будет, если мы кладем в HashMap ключ, у которого equals и hashCode определены некорректно?",
                " Возможна ли ситуация, когда HashMap выродится в список даже с ключами имеющими разные hashCode()?",
                "Почему нельзя использовать byte[] в качестве ключа в HashMap?",
                "Будет ли работать HashMap, если все добавляемые ключи будут иметь одинаковый hashCode()?",
                " Какое худшее время работы метода get(key) для ключа, которого нет в HashMap?",
                " Какое худшее время работы метода get(key) для ключа, который есть в HashMap?",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Коллекции");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.COLLECTION;
    }
}