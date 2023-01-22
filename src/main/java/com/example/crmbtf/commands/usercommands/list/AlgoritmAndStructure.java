package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.Questionnaire;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AlgoritmAndStructure implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое Big O? Как происходит оценка асимптотической сложности алгоритмов?",
                "Что такое рекурсия? Сравните преимущества и недостатки итеративных и рекурсивных алгоритмов",
                "Что такое жадные алгоритмы? Приведите пример",
                "Расскажите про пузырьковую сортировку",
                "Расскажите про быструю сортировку",
                "Расскажите про сортировку слиянием",
                "Расскажите про бинарное дерево",
                "Расскажите про красно-черное дерево",
                "Расскажите про линейный и бинарный поиск",
                "Расскажите про очередь и стек",
                "Сравните сложность вставки, удаления, поиска и доступа по индексу в ArrayList и LinkedList",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Алгоритмы и структуры данных");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.ALGORITM_AND_STRUCTURE;
    }
}