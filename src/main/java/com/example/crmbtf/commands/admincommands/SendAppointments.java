package com.example.crmbtf.commands.admincommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.service.AppointmentService;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class SendAppointments implements Command {
    @Autowired
    private AppointmentService appointmentService;
    @Override
    public void doCommand(ExecutionContext executionContext) {

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        List<AppointmentToDoctors> all = appointmentService.findAll().stream().filter(e -> e.getDate().toString().equals(formatForDateNow.format(date))).toList();
        all.forEach(e -> {


            Long chatId = e.getPatient().getChatId();
            executionContext.sendMessageToUserWithId("Напоминаем у вас запись к: \n"+e.getDoctor().getFirstName()+" "+e.getDoctor().getLastName() + "\n" + e.getDate() + "\n" + e.getTime(), String.valueOf(chatId));
        });


    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("Отправить уведомления");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.SEND_APPOINTMENT;
    }
}