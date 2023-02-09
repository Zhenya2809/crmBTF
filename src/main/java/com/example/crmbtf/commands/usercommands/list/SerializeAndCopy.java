package com.example.crmbtf.commands.usercommands.list;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SerializeAndCopy implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of("Что такое сериализация и как она реализована в Java?" ,
                "Для чего нужна сериализация?" ,
                "Опишите процесс сериализации/десериализации с использованием Serializable." ,
                "Как изменить стандартное поведение сериализации/десериализации?" ,
                "Какие поля не будут сериализованы при сериализации? Будет ли сериализовано final поле?" ,
                "Как создать собственный протокол сериализации?" ,
                "Какая роль поля serialVersionUID в сериализации?" ,
                "Когда стоит изменять значение поля serialVersionUID?" ,
                "В чем проблема сериализации Singleton?" ,
                "Расскажите про клонирование объектов." ,
                "В чем отличие между поверхностным и глубоким клонированием?" ,
                "Какой способ клонирования предпочтительней?" ,
                "Почему метод clone() объявлен в классе Object, а не в интерфейсе Cloneable?" ,
                "Как создать глубокую копию объекта? (2 способа)  ",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Сериализация и копирование");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.SERIALIZE_AND_COPY;
    }
}