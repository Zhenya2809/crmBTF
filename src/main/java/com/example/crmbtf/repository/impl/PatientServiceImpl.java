package com.example.crmbtf.repository.impl;

import com.example.crmbtf.mapper.ConfigMapper;
import com.example.crmbtf.model.Patient;
import com.example.crmbtf.model.PatientCard;
import com.example.crmbtf.model.User;
import com.example.crmbtf.model.dto.PatientDto;
import com.example.crmbtf.repository.PatientRepository;
import com.example.crmbtf.repository.TelegramUsersRepository;
import com.example.crmbtf.repository.UserRepository;
import com.example.crmbtf.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {


    @PersistenceContext
    private EntityManager entityManager;
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

    public Patient createNew(String email, String fio, String phone) {
        Patient patient = new Patient();
        patient.setEmail(email);
        patient.setFio(fio);
        patient.setPhoneNumber(phone);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient findPatientByEmailOrCreatePatient(String email, Long chatId) {
        Optional<Patient> byEmail = patientRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            log.info("In fundPatientByEmail patient:{} found by email:{}", byEmail.get(), email);
            return byEmail.get();
        }
        Patient patient = new Patient();
        patient.setChatId(chatId);
        patient.setEmail(email);
        patientRepository.save(patient);
        log.info("IN findPatientByEmail patient not found and created new patient with email:{}", email);
        return patient;
    }

    @Override
    public Patient findPatientByPhoneOrCreatePatient(String phone, Long chatId) {
        Optional<Patient> byPhone = patientRepository.findPatientByPhoneNumber(phone);
        if (byPhone.isPresent()) {
            log.info("In fundPatientByPhone patient:{} found by phone:{}", byPhone.get(), phone);
            return byPhone.get();
        }
        Patient patient = new Patient();
        patient.setChatId(chatId);
        patient.setPhoneNumber(phone);

        patientRepository.save(patient);
        log.info("IN fundPatientByPhone patient not found and created new patient with phone:{}", phone);
        return patient;
    }

    @Override
    public Patient findPatientByPhone(String phone) {
        Optional<Patient> byPhone = patientRepository.findPatientByPhoneNumber(phone);
        if (byPhone.isPresent()) {
            log.info("In fundPatientByPhone patient:{} found by phone:{}", byPhone.get(), phone);
            return byPhone.get();
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> all = patientRepository.findAll();
        log.info("IN findAll - {} patient found", all.size());
        return all;
    }

    @Override
    public List<Patient> getMyProfile() {
        List<Patient> patientList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> byUsername = userRepository.findByPhone(auth.getName());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            String email = user.getEmail();
            Optional<Patient> byEmail = patientRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                Patient patient = byEmail.get();
                patientList.add(patient);
            }
        }
        return patientList;
    }

    @Override
    public void updateProfile(PatientDto patientUpdate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> byUsername = userRepository.findByPhone(auth.getName());

        if (byUsername.isPresent()) {
            User user = byUsername.get();
            String email = user.getEmail();
            Optional<Patient> byEmail = patientRepository.findByEmail(email);
            if (byEmail.isPresent()) {
                Patient patient = byEmail.get();
                log.info("IN updateProfile Patient:{} found", patient);

                patient.setSex(patientUpdate.getSex());
                if (patientUpdate.getBirthday() != null) {

                    long currentDateTime = Long.parseLong(patientUpdate.getBirthday());
                    Date currentDate = new Date(currentDateTime);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    patient.setBirthday(df.format(currentDate));

                }
                if (patientUpdate.getInsurancePolicy() != null) {
                    patient.setInsurancePolicy(patientUpdate.getInsurancePolicy());
                }
                if (patientUpdate.getFio() != null) {
                    patient.setFio(patientUpdate.getFio());
                    String[] s = patientUpdate.getFio().split(" ");
                    if (s.length >= 1) {
                        user.setFirstName(s[0]);
                    }
                    if (s.length >= 2) {
                        user.setLastName(s[1]);
                    }

                }
                if (patientUpdate.getEmail() != null) {
                    patient.setEmail(patientUpdate.getEmail());
                    user.setEmail(patientUpdate.getEmail());
                }
                if (patientUpdate.getPlaceOfResidence() != null) {
                    patient.setPlaceOfResidence(patientUpdate.getPlaceOfResidence());
                }
                if (patientUpdate.getPhoneNumber() != null) {
                    patient.setPhoneNumber(patientUpdate.getPhoneNumber());
                    user.setPhone(patientUpdate.getPhoneNumber());
                }
                userRepository.save(user);
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

    @Override
    public List<Patient> searchPatientsByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> root = criteriaQuery.from(Patient.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.like(criteriaBuilder.lower(root.get("fio")), "%" + name.toLowerCase() + "%"));

        TypedQuery<Patient> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
