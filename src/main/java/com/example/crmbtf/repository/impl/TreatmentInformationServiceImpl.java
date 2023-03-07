package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.TreatmentInformation;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.repository.TreatmentInformationRepository;
import com.example.crmbtf.service.TreatmentInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Slf4j
@Service
public class TreatmentInformationServiceImpl implements TreatmentInformationService {
    private final TreatmentInformationRepository treatmentInformationRepository;
    private final UserServiceImpl userServiceIml;
    private final PatientServiceImpl patientServiceimpl;
    private final PatientCardServiceImpl patientCardServiceimpl;
    private final DoctorRepository doctorRepository;

    public TreatmentInformationServiceImpl(TreatmentInformationRepository treatmentInformationRepository, UserServiceImpl userServiceIml, PatientServiceImpl patientServiceimpl, PatientCardServiceImpl patientCardServiceimpl, DoctorRepository doctorRepository) {
        this.treatmentInformationRepository = treatmentInformationRepository;
        this.userServiceIml = userServiceIml;
        this.patientServiceimpl = patientServiceimpl;
        this.patientCardServiceimpl = patientCardServiceimpl;

        this.doctorRepository = doctorRepository;
    }

    @Override
    public void editTreatmentInformation(Long patientId,String diagnosis, String recommendations, String symptoms, String treatment, Long doctorId) {
        Date todayDate = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatForTime = new SimpleDateFormat("HH.mm");


        Patient patient = patientServiceimpl.findPatientById(patientId);


        Optional<PatientCard> patientCardByPatient = patientCardServiceimpl.findPatientCardByPatient(patient);
        if (patientCardByPatient.isPresent()) {

            TreatmentInformation treatmentInformation = new TreatmentInformation();
            treatmentInformation.setDiagnosis(diagnosis);
            treatmentInformation.setDate(formatForDate.format(todayDate));
            treatmentInformation.setTime(formatForTime.format(todayDate));
            treatmentInformation.setPatientCard(patientCardByPatient.get());
            treatmentInformation.setRecommendations(recommendations);
            treatmentInformation.setSymptoms(symptoms);

            treatmentInformation.setDoctor(doctorRepository.findDoctorById(doctorId).get());
            treatmentInformation.setTreatment(treatment);
            treatmentInformationRepository.save(treatmentInformation);
        }
    }
}
