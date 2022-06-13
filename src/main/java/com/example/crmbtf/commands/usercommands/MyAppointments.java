package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MyAppointments implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        try {
            List<ReplyButton> replyButtonList = List.of(new ReplyButton("Главное меню"));
            executionContext.buildReplyKeyboard("Мои записи к врачу", replyButtonList);
            Optional<TelegramUsers> dataUserByChatId = executionContext.getTelegramUsersService().findDataUserByChatId(executionContext.getChatId());
            Patient patientByEmail = executionContext.getPatientService().findPatientByEmail(dataUserByChatId.get().getEmail(), executionContext);
            executionContext.getAppointmentService().findAllByPatientId(patientByEmail
                    .getId()).forEach(e -> {
                Instant now = Instant.now();
                Instant yesterday = now.minus(1, ChronoUnit.DAYS);
                Date myDate = Date.from(yesterday);
                if (e.getDate().after(myDate)) {
                    executionContext.replyMessage(e.getDoctor().getFio() + "\n" + e.getDate() + "\n" + e.getTime());
                }
            });
            executionContext.setLocalState(null);
            executionContext.setGlobalState(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Мои записи");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.MY_APPOINTMENTS;
    }
}