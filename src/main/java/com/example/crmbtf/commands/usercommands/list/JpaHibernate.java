package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JpaHibernate implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое ORM? Что такое JPA? Что такое Hibernate?",
                        "Что такое EntityManager? Какие функции он выполняет?",
                        "Каким условиям должен удовлетворять класс чтобы являться Entity?",
                        "Может ли абстрактный класс быть Entity?",
                        "Может ли Entity класс наследоваться от не Entity классов (non-entity classes)?",
                        "Может ли Entity класс наследоваться от других Entity классов?",
                        "Может ли не Entity класс наследоваться от Entity класса?",
                        "Что такое встраиваемый (Embeddable) класс?  Какие требования JPA устанавливает к встраиваемым (Embeddable) классам?",
                        "Что такое Mapped Superclass?",
                        "Какие три типы стратегии наследования мапинга (Inheritance Mapping Strategies) описаны в JPA?",
                        "Как мапятся Enumы?",
                        "Как мапятся даты (до java 8 и после)?",
                        "Как “смапить” коллекцию примитивов?",
                        "Какие есть виды связей?",
                        "Что такое владелец связи?",
                        "Что такое каскады?",
                        " Какие два типа fetch стратегии в JPA вы знаете?",
                        "Какие четыре статуса жизненного цикла Entity объекта (Entity Instance’s Life Cycle) вы можете перечислить?",
                        "Как влияет операция persist на Entity объекты каждого из четырех статусов?",
                        "Как влияет операция remove на Entity объекты каждого из четырех статусов?",
                        "Как влияет операция merge на Entity объекты каждого из четырех статусов?",
                        "Как влияет операция refresh на Entity объекты каждого из четырех статусов?",
                        "Как влияет операция detach на Entity объекты каждого из четырех статусов?",
                        "Для чего нужна аннотация Basic?",
                        "Для чего нужна аннотация Column?",
                        "Для чего нужна аннотация Access?",
                        "Для чего нужна аннотация Cacheable?",
                        "Для чего нужны аннотации @Embedded и @Embeddable?",
                        "Как смапить составной ключ?",
                        "Для чего нужна аннотация ID? Какие @GeneratedValue вы знаете?",
                        "Расскажите про аннотации @JoinColumn и @JoinTable? Где и для чего они используются?",
                        "Для чего нужны аннотации @OrderBy и @OrderColumn, чем они отличаются?",
                        "Для чего нужна аннотация Transient?",
                        "Какие шесть видов блокировок (lock) описаны в спецификации JPA (или какие есть значения у enum LockModeType в JPA)?",
                        "Какие два вида кэшей (cache) вы знаете в JPA и для чего они нужны?",
                        "Как работать с кешем 2 уровня?",
                        "Что такое JPQL/HQL и чем он отличается от SQL?",
                        "Что такое Criteria API и для чего он используется?",
                        "Расскажите про проблему N+1 Select и путях ее решения.",
                        "Что такое EntityGraph? Как и для чего их использовать?","Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("JPA и Hibernate");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.JPA_HIBERNATE;
    }
}