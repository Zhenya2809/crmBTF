package com.example.crmbtf.service.impl;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.User;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Configurable
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;


    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public String createDoctor(String doctorFirstName,String doctorLastName, String speciality, String about, String photo, User user) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorFirstName);
        doctor.setLastName(doctorLastName);
        doctor.setSpeciality(speciality);
        doctor.setAbout(about);
        doctor.setPhoto(photo);
        doctor.setUser(user);
        doctorRepository.save(doctor);
        log.info("IN created - doctor:{} successfully created", doctor);
        return "Doctor :{" + doctor.getLastName() + "} successfully created";
    }

    @Override
    public Set<String> getDrSpeciality() {
        Set<String> specialityDr = new HashSet<>();
        List<Doctor> all = doctorRepository.findAll();
        all.forEach(e-> {
            String speciality = e.getSpeciality();
            specialityDr.add(speciality);
        });
        return specialityDr;
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
        log.info("IN delete - doctor with id: {} successfully deleted", id);
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        Optional<Doctor> doctorById = doctorRepository.findDoctorById(id);
        if (doctorById.isPresent()) {
            log.info("IN getDoctorById - doctor: {} found by id: {}", doctorById.get(), id);
        }
        return doctorById;
    }


    @Override
    public List<Doctor> findAll() {
        List<Doctor> result = doctorRepository.findAll();
        log.info("IN findAll - {} doctors found", result.size());
        return result;
    }

    @Override
    public List<Doctor> findDoctorsBySpeciality(String speciality) {
        List<Doctor> result = doctorRepository.findDoctorsBySpeciality(speciality);
        log.info("IN findDoctorsBySpeciality - {} doctors found", result.size());
        return result;
    }


    /// ПЕРЕРОБИТЬ ЦЕЙ МЄТОД ХУЙНЯ СОБАЧА
    @Override
    public Doctor findDoctorByFio(String firstName) {

        Doctor doctor = doctorRepository.findDoctorByFirstName(firstName);
        log.info("IN findDoctorByFio - doctor: {} found by fio: {}", doctor, firstName);
        return doctor;
    }

    @Override
    public Collection<Doctor> searchDoctor(String speciality, String fio) {
        Collection<Doctor> doctors = doctorRepository.searchDoctor(speciality, fio);
        log.info("IN searchDoctor by speciality:{} and fio:{} count:{} doctors found", speciality, fio, doctors.size());
        return doctors;
    }
}
