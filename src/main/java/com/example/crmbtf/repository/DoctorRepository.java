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

    Optional<Doctor> findByFirstNameAndLastName(String firstName, String lastName);

    List<Doctor> findDoctorsBySpeciality(String speciality);

    @Query(value = "select * from t_doctor where first_name like :firstName and last_name like :lastName", nativeQuery = true)
    Doctor findDoctorByFio(@Param("firstName") String firstName,
                           @Param("lastName") String lastName);

    @Query(value = "select * from t_doctor where lower(speciality) like %:speciality% and lower(fio) like %:fio% limit 50", nativeQuery = true)
    Collection<Doctor> searchDoctor(@Param("speciality") String speciality,
                                    @Param("fio") String fio);

    @Query(value = "select distinct speciality from t_doctor td ", nativeQuery = true)
    List<String> getAllSpeciality();
    Optional<Doctor> findDoctorByUserId(Long userID);
    @Query("SELECT d FROM Doctor d WHERE LOWER(d.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> searchByNameIgnoreCase(@Param("name") String name);
}
