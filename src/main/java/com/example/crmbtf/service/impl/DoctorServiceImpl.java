package com.example.crmbtf.service.impl;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.repository.DoctorRepository;
import com.example.crmbtf.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Configurable
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;


    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public String createDoctor(String doctorFio, String speciality, String about, String photo) {
        Doctor doctor = new Doctor();
        doctor.setFio(doctorFio);
        doctor.setSpeciality(speciality);
        doctor.setAbout(about);
        doctor.setPhoto(photo);
        doctorRepository.save(doctor);
        log.info("IN created - doctor:{} successfully created", doctor);
        return "Doctor :{" + doctor.getFio() + "} successfully created";
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

    @Override
    public Doctor findDoctorByFio(String fio) {

        Doctor doctor = doctorRepository.findDoctorsByFio(fio);
        log.info("IN findDoctorByFio - doctor: {} found by fio: {}", doctor, fio);
        return doctor;
    }

    @Override
    public Collection<Doctor> searchDoctor(String speciality, String fio) {
        Collection<Doctor> doctors = doctorRepository.searchDoctor(speciality, fio);
        log.info("IN searchDoctor by speciality:{} and fio:{} count:{} doctors found", speciality, fio, doctors.size());
        return doctors;
    }
}
