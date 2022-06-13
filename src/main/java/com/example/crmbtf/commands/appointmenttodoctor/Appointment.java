package com.example.crmbtf.commands.appointmenttodoctor;


import com.example.crmbtf.commands.appointmenttodoctor.localstate.LocalStateForAppointment;
import com.example.crmbtf.telegram.ExecutionContext;

public interface Appointment {
   void execute(ExecutionContext executionContext, LocalStateForAppointment localStateForAppointment );
}
