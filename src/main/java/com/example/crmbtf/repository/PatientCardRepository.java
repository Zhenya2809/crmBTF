package com.example.crmbtf.repository;


import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PatientCardRepository extends JpaRepository<PatientCard, Long> {

    PatientCard findPatientCardById(Long id);
    Optional<PatientCard> findPatientCardByPatient(Patient patient);
    PatientCard findPatientCardByPatientId(Long id);

}
