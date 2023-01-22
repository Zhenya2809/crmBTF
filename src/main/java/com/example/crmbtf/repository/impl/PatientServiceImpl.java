package com.example.crmbtf.repository.impl;

import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.PatientDTO;
import com.example.crmbtf.model.dto.PatientUpdateDTO;
import com.example.crmbtf.repository.PatientRepository;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.PatientService;
import com.example.crmbtf.telegram.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;
    private final PatientCardServiceImpl patientCardService;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, PatientCardServiceImpl patientCardService, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.patientCardService = patientCardService;
        this.userRepository = userRepository;
    }

    @Override
    public void createPatient(String fio, String birthday, String sex, String placeOfResidence, String insurancePolicy, String phoneNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = new Patient();
        patient.setFio(fio);
        patient.setBirthday(birthday);
        patient.setSex(sex);
        patient.setPlaceOfResidence(placeOfResidence);
        patient.setInsurancePolicy(insurancePolicy);
        patient.setEmail(auth.getName());
        patient.setPhoneNumber(phoneNumber);
        patientRepository.save(patient);
        log.info("IN createPatient  patient:{} is successfully created", patient);
    }

    @Override
    public void save(Patient patient) {
        Patient save = patientRepository.save(patient);
        log.info("IN save patient:{} is successfully created", save);

    }

    public void createNew(String email, String fio) {
        Patient patient = new Patient();
        patient.setEmail(email);
        patient.setFio(fio);
    }

    @Override
    public Patient findPatientByEmail(String email, ExecutionContext executionContext) {
        Optional<Patient> byEmail = patientRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            log.info("In fundPatientByEmail patient:{} found by email:{}", byEmail.get(), email);
            return byEmail.get();
        }
        Patient patient = new Patient();
        patient.setChatId(executionContext.getChatId());
        patient.setEmail(email);
        patient.setFio(executionContext.getFirstName() + " " + executionContext.getLastName());
        patientRepository.save(patient);
        log.info("IN findPatientByEmail patient:{} not found and created new patient:{} with email:{}", patient, patient, email);
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> all = patientRepository.findAll();
        log.info("IN findAll - {} patient found", all.size());
        return all;
    }

    @Override
    public List<PatientDTO> getMyProfile() {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> byUsername = userRepository.findByUsername(auth.getName());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            String email = user.getEmail();
            Optional<Patient> byEmail = patientRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                Patient patient = byEmail.get();
                PatientDTO patientDTO = PatientDTO.fromPatient(patient);
                patientDTOList.add(patientDTO);
            }
        }
        return patientDTOList;
    }

    @Override
    public void updateProfile(PatientUpdateDTO patientUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> byUsername = userRepository.findByUsername(auth.getName());

        if (byUsername.isPresent()) {
            User user = byUsername.get();
            String email = user.getEmail();
            Optional<Patient> byEmail = patientRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                Patient patient = byEmail.get();
                log.info("IN updateProfile Patient:{} found", patient);

                patient.setSex(patientUpdate.getSex());
                if (patientUpdate.getBirthday()!=null) {

                    long currentDateTime = Long.parseLong(patientUpdate.getBirthday());
                    //creating Date from millisecond
                    Date currentDate = new Date(currentDateTime);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    patient.setBirthday(df.format(currentDate));

                }
                if (patientUpdate.getInsurancePolicy()!=null) {
                    patient.setInsurancePolicy(patientUpdate.getInsurancePolicy());
                }
                if (patientUpdate.getPlaceOfResidence()!=null) {
                    patient.setPlaceOfResidence(patientUpdate.getPlaceOfResidence());
                }
                if (patientUpdate.getPhoneNumber()!=null) {
                    patient.setPhoneNumber(patientUpdate.getPhoneNumber());
                }
                patientRepository.save(patient);
            }
        }
    }

    @Override
    public Optional<Patient> findById(Long id) {
        Optional<Patient> result = patientRepository.findById(id);
        log.info("IN findById Optional<Patient>:{} found by id:{}", result, id);
        return result;
    }

    @Override
    public Patient findPatientById(Long id) {
        Patient patientById = patientRepository.findPatientById(id);
        log.info("IN findPatientById patient:{} found by id:{}", patientById, id);
        return patientById;
    }

    @Override
    public Optional<Patient> findPatientByFio(String name) {
        Optional<Patient> patientByFio = patientRepository.findPatientByFio(name);
        log.info("IN findPatientByFio Optional<Patient>:{} found by name:{}", patientByFio, name);
        return patientByFio;
    }

    @Override
    public Optional<Patient> findPatientByFioContains(String fio) {
        Optional<Patient> patientByFioContains = patientRepository.findPatientByFioContains(fio);
        log.info("IN findPatientByFioContains Optional<Patient>:{} found by fio:{}", patientByFioContains, fio);
        return patientByFioContains;
    }

    @Override
    public Optional<Patient> findPatientByAuthEmail() {
        return patientRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());//peredelat'
    }

    @Override
    public void editPatient(Long id, String birthday, String insurancePolicy, String placeOfResidence, String sex, String fio, String phoneNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = patientRepository.findPatientById(id);
        patient.setBirthday(birthday);
        patient.setEmail(auth.getName());
        patient.setInsurancePolicy(insurancePolicy);
        patient.setPlaceOfResidence(placeOfResidence);
        patient.setSex(sex);
        patient.setFio(fio);
        patient.setPhoneNumber(phoneNumber);
        patientRepository.save(patient);
        log.info("IN editPatient patient:{} is successfully edited", patient);

    }

    @Override
    public Optional<PatientCard> findPatientCardByPatient(Patient patient) {

        Optional<PatientCard> patientCardByPatient = patientCardService.findPatientCardByPatient(patient);
        log.info("IN findPatientCardByPatient Optional<PatientCard>:{} found by patient:{}", patientCardByPatient, patient);
        return patientCardByPatient;
    }
}
