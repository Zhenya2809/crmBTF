package com.example.crmbtf.repository;


import com.example.crmbtf.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findDoctorById(Long id);

    List<Doctor> findDoctorsBySpeciality(String speciality);
    @Query(value = "select * from t_doctor where first_name like :firstName and last_name like :lastName", nativeQuery = true)
    Doctor findDoctorByFio(@Param("firstName") String firstName,
                           @Param("lastName") String lastName);

    Doctor findDoctorByUserId(Long id);

    @Query(value = "select * from t_doctor where lower(speciality) like %:speciality% and lower(fio) like %:fio% limit 50", nativeQuery = true)
    Collection<Doctor> searchDoctor(@Param("speciality") String speciality,
                                    @Param("fio") String fio);

    @Query(value = "select distinct speciality from t_doctor td ", nativeQuery = true)
    List<String> getAllSpeciality();
}
