package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.Patient;
import lombok.Data;


@Data
public class PatientDTO {

    private String id;
    private String fio;

    private String sex;

    private String birthday;

    private String placeOfResidence;

    private String insurancePolicy;

    private String email;

    private String phoneNumber;

    public static PatientDTO fromPatient(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(String.valueOf(patient.getId()));
        patientDTO.setFio(patient.getFio());
        patientDTO.setSex(patient.getSex());
        patientDTO.setBirthday(patient.getBirthday());
        patientDTO.setPlaceOfResidence(patient.getPlaceOfResidence());
        patientDTO.setInsurancePolicy(patient.getInsurancePolicy());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        return patientDTO;
    }
}
