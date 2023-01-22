package com.example.crmbtf.commands.appointmenttodoctor;



import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;

import java.time.LocalDate;
import java.util.List;

public class ChoseId implements Appointment {


    @Override
    public void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment) {


        LocalDate today = LocalDate.now();
        String inputMessage = executionContext.getUpdate().getMessage().getText();
        String[] split = inputMessage.split(" ");
        String firstName = split[0];
        String lastName = split[1];
        localStateForAppointment.setDoctorId(executionContext.getDoctorService().findDoctorByFio(firstName,lastName).getId());
        List<ReplyButton> replyButtons = List.of(new ReplyButton(today.toString()),
                new ReplyButton(today.plusDays(1).toString()),
                new ReplyButton(today.plusDays(2).toString()),
                new ReplyButton(today.plusDays(3).toString()),
                new ReplyButton(today.plusDays(4).toString()));
        executionContext.buildReplyKeyboard("На какое число вы хотите записаться?", replyButtons);
        localStateForAppointment.setStep("chose_data_to_appointment");


    }
}
