package com.example.crmbtf.service;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;

import java.util.Optional;

public interface PatientCardService {
    PatientCard findPatientCardById(Long id);
    void save(PatientCard patientCard);
    PatientCard findPatientCardByPatientId(Long id);
    Optional<PatientCard> findPatientCardByPatient(Patient patient);
}
