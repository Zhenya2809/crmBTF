
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Benefits implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Платформенная независимость. Наш класс, написанный на Java компилируется в платформно-независимый байт-код, который интерпретируется и исполняется JVM.\n" +
                "Простота. Синтаксис языка крайне прост в изучении. От нас требуется лишь понимание основ ООП, потому что Java является полностью объектно-ориентированной.\n" +
                "Переносимость. Тот факт, что Java не реализовывает специфичных аспектов для каждого типа машин, делает программы, написанные с её использованием переносимыми.\n" +
                "Объектно-ориентированность. Всё сущности в Java являются объектами, что позволяет нам использовать всю мощь ООП.\n" +
                "Безопасность. Безопасность Java позволяет разрабатывать системы, защищенные от вирусов и взломов. Авторизация в Java основана на шифровании открытого ключа.\n" +
                "Устойчивость. Защита от ошибок обеспечивается за счёт проверок во время компиляции и во время непосредственного выполнения программ.\n" +
                "Интерпретируемость. Байт-код Java транслируется в машинные команды “на лету” и нигде не хранится.\n" +
                "Распределенность. Java разработана для распределённого окружения Internet.\n" +
                "Многопоточность. Язык поддерживает многопоточность (одновременное выполнение нескольких задач), что позволяет нам создавать хорошо отлаженные приложения\n" +
                "Производительность. Использование JIT (Just-In-Time) компилятора, позволяет достигать высоких показателей.\n" +
                " \n");
    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Какие преимущества у Java?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.BENEFITS;
    }
}