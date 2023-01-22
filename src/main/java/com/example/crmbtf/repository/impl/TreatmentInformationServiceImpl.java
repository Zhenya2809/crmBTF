package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.TreatmentInformation;
import com.example.crmbtf.repository.TreatmentInformationRepository;
import com.example.crmbtf.service.TreatmentInformationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TreatmentInformationServiceImpl implements TreatmentInformationService {
    private final TreatmentInformationRepository treatmentInformationRepository;
    private final UserServiceImpl userServiceIml;
    private final PatientServiceImpl patientServiceimpl;
    private final PatientCardServiceImpl patientCardServiceimpl;

    public TreatmentInformationServiceImpl(TreatmentInformationRepository treatmentInformationRepository, UserServiceImpl userServiceIml, PatientServiceImpl patientServiceimpl, PatientCardServiceImpl patientCardServiceimpl) {
        this.treatmentInformationRepository = treatmentInformationRepository;
        this.userServiceIml = userServiceIml;
        this.patientServiceimpl = patientServiceimpl;
        this.patientCardServiceimpl = patientCardServiceimpl;
    }

    @Override
    public void editTreatmentInformation(Long id, String diagnosis, String recommendations, String symptoms, String treatment) {
        Date todayDate = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatForTime = new SimpleDateFormat("HH.mm");

        //      Doctor doctor = userServiceIml.findDoctorByLogin();//Переделать
        Patient patient = patientServiceimpl.findPatientById(id);


        Optional<PatientCard> patientCardByPatient = patientCardServiceimpl.findPatientCardByPatient(patient);
        if (patientCardByPatient.isPresent()) {

            TreatmentInformation treatmentInformation = new TreatmentInformation();
       //     treatmentInformation.setDoctor(doctor);
            treatmentInformation.setDiagnosis(diagnosis);
            treatmentInformation.setDate(formatForDate.format(todayDate));
            treatmentInformation.setTime(formatForTime.format(todayDate));
            treatmentInformation.setPatientCard(patientCardByPatient.get());
            treatmentInformation.setRecommendations(recommendations);
            treatmentInformation.setSymptoms(symptoms);
            treatmentInformation.setTreatment(treatment);
            treatmentInformationRepository.save(treatmentInformation);
        }
    }
}
