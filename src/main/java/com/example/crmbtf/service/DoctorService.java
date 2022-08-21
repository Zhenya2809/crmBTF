package com.example.crmbtf.service;

import com.example.crmbtf.model.Doctor;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DoctorService {
    String createDoctor(String doctorFio, String speciality, String about, String photo);

    Set<String> getDrSpeciality();
    void deleteDoctor(Long id);

    Optional<Doctor> getDoctorById(Long id);

    List<Doctor> findAll();

    List<Doctor> findDoctorsBySpeciality(String speciality);

    Doctor findDoctorByFio(String fio);

    Collection<Doctor> searchDoctor(@Param("speciality") String speciality,
                                    @Param("fio") String fio);
}
