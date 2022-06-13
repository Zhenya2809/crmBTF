package com.example.crmbtf.commands.registration;


import com.example.crmbtf.commands.registration.rolemenu.Admin;
import com.example.crmbtf.commands.registration.rolemenu.ChoseRole;
import com.example.crmbtf.commands.registration.rolemenu.Doctor;
import com.example.crmbtf.commands.registration.rolemenu.User;
import com.example.crmbtf.telegram.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class Authorized implements Registration {
    @Override
    public void execute(ExecutionContext executionContext) {

        String role = executionContext.getUser().getRole();
        Map<String, ChoseRole> map= new HashMap<>();
        map.put("USER",new User());
        map.put("ADMIN",new Admin());
        map.put("DOCTOR",new Doctor());
        ChoseRole choseRole = map.get(role);
        if (choseRole==null){
            throw new RuntimeException("fail to find by role");
        }
        choseRole.execute(executionContext);
        executionContext.setLocalState(null);
        executionContext.setGlobalState(null);
    }
}
