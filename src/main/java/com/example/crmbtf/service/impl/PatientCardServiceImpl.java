package com.example.crmbtf.service.impl;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.repository.PatientCardRepository;
import com.example.crmbtf.service.PatientCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PatientCardServiceImpl implements PatientCardService {
    private final PatientCardRepository patientCardRepository;


    public PatientCardServiceImpl(PatientCardRepository patientCardRepository) {
        this.patientCardRepository = patientCardRepository;
    }

    @Override
    public PatientCard findPatientCardById(Long id) {
        PatientCard patientCardById = patientCardRepository.findPatientCardById(id);
        log.info("IN findPatientCardById PatientCard:{} found by id:{}", patientCardById, id);
        return patientCardById;
    }

    @Override
    public void save(PatientCard patientCard) {
        patientCardRepository.save(patientCard);
        log.info("IN save PatientCard:{} successfully saved", patientCard);

    }

    @Override
    public PatientCard findPatientCardByPatientId(Long id) {
        PatientCard patientCardByPatientId = patientCardRepository.findPatientCardByPatientId(id);
        log.info("IN findPatientCardByPatientId   PatientCard:{} found by id:{}", patientCardByPatientId, id);
        return patientCardByPatientId;
    }

    @Override
    public Optional<PatientCard> findPatientCardByPatient(Patient patient) {

        Optional<PatientCard> patientCardByPatient = patientCardRepository.findPatientCardByPatient(patient);
        log.info("IN findPatientCardByPatient Optional<PatientCard>:{} found by patient:{}", patientCardByPatient, patient);
        return patientCardByPatient;
    }
}
