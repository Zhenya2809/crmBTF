package com.example.crmbtf.commands.appointmenttodoctor;


import com.example.crmbtf.Constants;
import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;
import com.example.crmbtf.telegram.inline.InlineButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SendDoctorSpeciality implements Appointment {

    @Override
    public void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment) {




        List<String> allSpeciality = executionContext.getDoctorService().getAllSpeciality();
        List<InlineButton> inlineButtons = allSpeciality.stream().map(e -> new InlineButton(e, Constants.site + "choseDoctor/" + e)).toList();


        List<ReplyButton> replyButtons = allSpeciality.stream().map(ReplyButton::new).toList();
        executionContext.buildInlineKeyboard("перейти на сайт для записи", inlineButtons);
        executionContext.buildReplyKeyboard("Продолжить через телеграмм", replyButtons);
        localStateForAppointment.setStep("chose_doctor");


    }
}
