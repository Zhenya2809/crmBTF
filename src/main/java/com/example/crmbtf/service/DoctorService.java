package com.example.crmbtf.service;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.User;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DoctorService {
    String createDoctor(String doctorFirstName, String doctorLastName, String speciality, String about, String photo, User user);

    Set<String> getDrSpeciality();
    void deleteDoctor(Long id);

    Optional<Doctor> getDoctorById(Long id);

    List<Doctor> findAll();

    List<Doctor> findDoctorsBySpeciality(String speciality);

    Doctor findDoctorByFio(String firstName,String lastName);

    Collection<Doctor> searchDoctor(@Param("speciality") String speciality,
                                    @Param("fio") String fio);
    List<String> getAllSpeciality();
}
