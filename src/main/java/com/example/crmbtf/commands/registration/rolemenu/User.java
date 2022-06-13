package com.example.crmbtf.commands.registration.rolemenu;


import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class User implements ChoseRole {
    @Override
    public void execute(ExecutionContext executionContext) {
        log.info("SEND ROLE MENU - USER");
        Patient patient = executionContext.getPatientService().findPatientByEmail(executionContext.getTelegramUsersService()
                .findDataUserByChatId(executionContext.getChatId()).get().getEmail(), executionContext);
        List<AppointmentToDoctors> allByPatientId = executionContext.getAppointmentService().findAllByPatientId(patient.getId());
        if (allByPatientId.size() > 0) {
            List<ReplyButton> replyButtonList = List.of(new ReplyButton("О нас"),
                    new ReplyButton("Специалисты"),
                    new ReplyButton("Услуги"),
                    new ReplyButton("Наш адрес"),
                    new ReplyButton("Мои записи"));

            executionContext.buildReplyKeyboard("О, как так? Тогда нам есть о чем поговорить! \n" +
                    "так хочеться рассказать тебе о нас", replyButtonList);
        } else {

            List<ReplyButton> replyButtonList = List.of(new ReplyButton("О нас"),
                    new ReplyButton("Специалисты"),
                    new ReplyButton("Услуги"),
                    new ReplyButton("Наш адрес"));

            executionContext.buildReplyKeyboard("О, как так? Тогда нам есть о чем поговорить! \n" +
                    "так хочеться рассказать тебе о нас", replyButtonList);
        }
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);


    }
}
