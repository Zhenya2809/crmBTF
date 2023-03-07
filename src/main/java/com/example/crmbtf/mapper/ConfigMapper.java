package com.example.crmbtf.mapper;
import com.example.crmbtf.model.*;
import com.example.crmbtf.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfigMapper {

    ConfigMapper INSTANCE = Mappers.getMapper(ConfigMapper.class);

    List<User> toUsers(List<UserDto> userDtos);

    List<UserDto> toUserDtos(List<User> users);

    List<AppointmentToDoctorDTO> toAppointmentToDoctorDtos(List<AppointmentToDoctors> appointmentToDoctors);

    List<DoctorDto> toDoctorDtos(List<Doctor> doctor);

    List<AppointmentToDoctors> toAppointmentToDoctors(List<AppointmentToDoctorDTO> appointmentToDoctorsDTO);

    List<Doctor> toDoctor(List<DoctorDto> doctorsDto);

    List<PatientDto> toPatientDtoList(List<Patient> patients);

    List<Patient> toPatientList(List<PatientDto> patientsDto);

    List<TelegramUserDto> toTelegramUserDtoList(List<TelegramUser> telegramUsers);

    List<TelegramUser> toTelegramUserList(List<TelegramUserDto> telegramUsersDtos);

    AppointmentToDoctors toAppointmentToDoctor(AppointmentToDoctorDTO appointmentToDoctorDTO);

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    AppointmentToDoctorDTO toAppointmentToDoctorDto(AppointmentToDoctors appointmentToDoctors);

    DoctorDto toDoctorDto(Doctor doctor);

    Doctor toDoctor(DoctorDto docDto);

    PatientDto toPatientDto(Patient patient);

    Patient toPatientDto(PatientDto patientDTO);

    TelegramUserDto toTelegramUserDto(TelegramUser telegramUser);

    TelegramUser toTelegramUser(TelegramUserDto telegramUserDto);

}