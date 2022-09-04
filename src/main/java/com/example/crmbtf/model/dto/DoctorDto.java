package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {
    private Long id;
    private String doctorFirstName;
    private String doctorLastName;
    private String speciality;
    private String about;
    private String linkPhoto;
    private Long userId;
    private String login;
    private String password;
    private String rePassword;
    private String email;


    public static DoctorDto fromDoctor(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setDoctorFirstName(doctor.getFirstName());
        doctorDto.setDoctorLastName(doctor.getLastName());
        doctorDto.setSpeciality(doctor.getSpeciality());
        doctorDto.setAbout(doctor.getAbout());
        doctorDto.setLinkPhoto(doctor.getPhoto());

        return doctorDto;
    }
}
