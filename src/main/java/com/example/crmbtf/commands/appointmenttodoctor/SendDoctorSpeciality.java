package com.example.crmbtf.commands.appointmenttodoctor;


import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;
import com.example.crmbtf.telegram.inline.InlineButton;

import java.util.List;

public class SendDoctorSpeciality implements Appointment {
    @Override
    public void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment) {


        List<Doctor> all = executionContext.getDoctorService().findAll();
        List<InlineButton> inlineButtons = all.stream().map(doctor -> {
            String speciality = doctor.getSpeciality();
            Long id = doctor.getId();
            return new InlineButton(speciality, "http://95.216.146.138:8080/clinic/" + id);
        }).toList();
        List<ReplyButton> replyButtons = all.stream().map(e -> new ReplyButton(e.getSpeciality())).toList();
        executionContext.buildInlineKeyboard("перейти на сайт для записи", inlineButtons);
        executionContext.buildReplyKeyboard("Продолжить через телеграмм", replyButtons);
        localStateForAppointment.setStep("chose_doctor");


    }
}
