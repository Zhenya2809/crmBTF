package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.Doctor;
import com.example.crmbtf.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {
    private Long id;
    private String doctorFIO;
    private String speciality;
    private String about;
    private String linkPhoto;



    public static DoctorDto fromDoctor(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setDoctorFIO(doctor.getFio());
        doctorDto.setSpeciality(doctor.getSpeciality());
        doctorDto.setAbout(doctor.getAbout());
        doctorDto.setLinkPhoto(doctor.getPhoto());

        return doctorDto;
    }
}
