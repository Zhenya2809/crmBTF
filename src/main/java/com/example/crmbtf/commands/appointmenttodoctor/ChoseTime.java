package com.example.crmbtf.commands.appointmenttodoctor;


import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.telegram.ExecutionContext;
public class ChoseTime implements Appointment {


    @Override
    public void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment) {
        Long docId = localStateForAppointment.getDoctorId();
        String inputMessage = executionContext.getInputText();

//        executionContext.createAppointmentToDoctor(
//                localStateForAppointment.getDate(),
//                inputMessage + ":00",
//                String.valueOf(docId),
//                executionContext);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);

    }
}
