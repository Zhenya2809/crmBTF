package com.example.crmbtf.repository;


import com.example.crmbtf.model.AppointmentToDoctors;
import com.example.crmbtf.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentToDoctors, Long> {
    List<AppointmentToDoctors> findAppointmentToDoctorsByDoctor(Doctor doctor);
    List<AppointmentToDoctors> findAllByDoctor_Id(Long id);
    List<AppointmentToDoctors> findAllByPatientId(Long id);
    AppointmentToDoctors findAppointmentToDoctorsByDoctorsappointmentsID(Long id);


}

