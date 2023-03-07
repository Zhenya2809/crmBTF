package com.example.crmbtf.service;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    void createPatient(String fio, String birthday, String sex, String placeOfResidence, String insurancePolicy, String phoneNumber);

    void save(Patient patient);

    Patient createNew(String email,String fio,String phone);


//    Patient findPatientByEmailE(String email, ExecutionContext executionContext);
    Patient findPatientByEmailOrCreatePatient(String email, Long chatId);
    List<Patient> findAll();
    Patient findPatientByPhone(String phone);
    List<Patient> searchPatientsByName(String name);
    void updateProfile(PatientDto patientDto);
    Patient findPatientByPhoneOrCreatePatient(String phone, Long chatId);
    List<Patient> getMyProfile();

    Optional<Patient> findById(Long id);

    Patient findPatientById(Long id);

    Optional<Patient> findPatientByFio(String name);

    Optional<Patient> findPatientByFioContains(String fio);

    Optional<Patient> findPatientByAuthEmail();

    void editPatient(Long id, String birthday, String insurancePolicy, String placeOfResidence, String sex, String fio, String phoneNumber);

    Optional<PatientCard> findPatientCardByPatient(Patient patient);
}
