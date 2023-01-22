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
public class Spring implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<String> list = new java.util.ArrayList<>(List.of(
                "Что такое инверсия контроля (IoC) и внедрение зависимостей (DI)? Как эти принципы реализованы в Spring?",
                "Что такое IoC контейнер?",
                "Что такое Bean в спринге? ",
                "Расскажите про аннотацию @Bean?",
                "Расскажите про аннотацию @Component?",
                "Чем отличаются аннотации @Bean и @Component?",
                "Расскажите про аннотации @Service и @Repository. Чем они отличаются?",
                "Расскажите про аннотацию @Autowired",
                "Расскажите про аннотацию @Resource",
                "Расскажите про аннотацию @Inject",
                "Расскажите про аннотацию @Lookup",
                "Можно ли вставить бин в статическое поле? Почему?",
                "Расскажите про аннотации @Primary и @Qualifier",
                "Как заинжектить примитив?",
                "Как заинжектить коллекцию?",
                "Расскажите про аннотацию @Conditional",
                "Расскажите про аннотацию @Profile",
                "Расскажите про ApplicationContext и BeanFactory,  чем отличаются? В каких случаях что стоит использовать?",
                "Расскажите про жизненный цикл бина, аннотации @PostConstruct и @PreDestroy()",
                "Расскажите про скоупы бинов? Какой скоуп используется по умолчанию? Что изменилось в пятом спринге?",
                "Расскажите про аннотацию @ComponentScan",
                "Как спринг работает с транзакциями? Расскажите про аннотацию @Transactional.",
                "Расскажите про аннотации @Controller и @RestController. Чем они отличаются? Как вернуть ответ со своим статусом (например 213)?",
                "Что такое ViewResolver?",
                "Чем отличаются Model, ModelMap и ModelAndView?",
                "Расскажите про паттерн MVC, как он реализован в Spring?",
                "Расскажите про паттерн Front Controller, как он реализован в Spring?",
                "Что такое АОП? Как реализовано в спринге?",
                "В чем разница между Filters, Listeners and Interceptors?",
                "Можно ли передать в запросе один и тот же параметр несколько раз? Как?",
                "Как работает Spring Security? Как сконфигурировать? Какие интерфейсы используются?",
                "Что такое SpringBoot? Какие у него преимущества? Как конфигурируется? Подробно.",
                "Расскажите про нововведения Spring 5.",
                "Назад"
        ));


        executionContext.buildReplyKeyboardWithStringList("Выберите вопрос", list);

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("SPRING");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.SPRING;
    }
}