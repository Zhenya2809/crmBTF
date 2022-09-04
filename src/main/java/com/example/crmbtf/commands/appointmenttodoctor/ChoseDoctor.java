package com.example.crmbtf.commands.appointmenttodoctor;



import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.telegram.ExecutionContext;

import java.util.List;

public class ChoseDoctor implements Appointment {

    @Override
    public void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment) {

        String inputMessage = executionContext.getUpdate().getMessage().getText();

        List<Doctor> doctorsBySpeciality = executionContext.getDoctorService().findDoctorsBySpeciality(inputMessage);
                doctorsBySpeciality.forEach(e -> {
            String speciality = e.getSpeciality();
            String fio = e.getFirstName()+" "+ e.getLastName();
            executionContext.replyImage(e.getPhoto());
            executionContext.replyMessage(e.getAbout());
            executionContext.replyMessage(speciality + " " + fio);
        });
        List<ReplyButton> doctorsFIOListForButton = doctorsBySpeciality.stream().map(e -> new ReplyButton(e.getFirstName()+" "+e.getLastName())).toList();
        executionContext.buildReplyKeyboard("Выберите доктора", doctorsFIOListForButton);
        localStateForAppointment.setStep("chose_id");


    }
}
