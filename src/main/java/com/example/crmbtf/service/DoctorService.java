package com.example.crmbtf.service;

import com.example.crmbtf.model.Doctor;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DoctorService {
    void createrDoctor(String doctorFio, String speciality, String about, String photo);

    void deleteDoctor(Long id);

    Doctor getDoctorById(Long id);

    List<Doctor> findAll();

    List<Doctor> findDoctorsBySpeciality(String speciality);

    Doctor findDoctorByFio(String fio);

    Collection<Doctor> searchDoctor(@Param("speciality") String speciality,
                                    @Param("fio") String fio);
}
