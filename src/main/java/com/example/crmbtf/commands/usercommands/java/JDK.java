
package com.example.crmbtf.commands.usercommands.java;

import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JDK implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        executionContext.replyMessage("Java Development Kit – инструменты для разработки\n" +
                " \n" +
                "Входит:\n" +
                "компилятор из исходного текста в байт-коды javac;\n" +
                "интерпретатор java, содержащий реализацию JVM;\n" +
                "облегченный интерпретатор jre (в последних версиях отсутствует);\n" +
                "программу просмотра апплетов appletviewer, заменяющую браузер;\n" +
                "отладчик jdb;\n" +
                "дизассемблер javap;\n" +
                "программу архивации и сжатия jar;\n" +
                "программу сбора и генерирования документации javadoc;\n" +
                "программу генерации заголовочных файлов языка С для создания \"родных\" методов javah;\n" +
                "программу генерации электронных ключей keytool;\n" +
                "программу native2ascii, преобразующую бинарные файлы в текстовые;\n" +
                "программы rmic и rmiregistry для работы с удаленными объектами;\n" +
                "программу serialver, определяющую номер версии класса;\n" +
                "библиотеки и заголовочные файлы \"родных\" методов;\n" +
                "библиотеку классов Java API (Application Programming Interface).\n" +
                " \n" +
                " \n" +
                " \n" +
                "Java Runtime Environment — минимальная реализация виртуальной машины, необходимая для исполнения Java-приложений, без компилятора и других средств разработки.\n" +
                " \n" +
                "Это просто пакет, который включает в себя JVM и минимальный набор библиотек для работы программ. И еще браузерный плагин, где будут выполняться апплеты.\n" +
                " \n");}

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Что такое JDK? Что в него входит?");
    }

    @Override
    public TelegramUser.botstate getGlobalState() {
        return TelegramUser.botstate.JDK;
    }
}