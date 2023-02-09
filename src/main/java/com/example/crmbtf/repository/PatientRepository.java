package com.example.crmbtf.repository;


import com.example.crmbtf.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Patient findPatientById(Long id);
    Patient findPatientByEmail(String email);
    Optional<Patient> findPatientByFio(String fio);

    Optional<Patient> findPatientByFioContains(String fio);


}
