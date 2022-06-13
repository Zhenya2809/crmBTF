package com.example.crmbtf.repository;



import com.example.crmbtf.model.TreatmentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentInformationRepository extends JpaRepository<TreatmentInformation, Long> {
//    Optional<TreatmentInformation> findTreatmentInformationByPatient(Patient patient);
    TreatmentInformation findTreatmentInformationByPatientCardId(Long id);
}
