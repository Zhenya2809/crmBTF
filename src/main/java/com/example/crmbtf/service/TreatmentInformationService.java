package com.example.crmbtf.service;

public interface TreatmentInformationService {
    void editTreatmentInformation(Long patientId, String diagnosis, String recommendations, String symptoms, String treatment,Long doctorId);
}
