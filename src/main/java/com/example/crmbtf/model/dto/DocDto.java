package com.example.crmbtf.model.dto;

import com.example.crmbtf.model.Doctor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DocDto {
    private Long id;
    private String doctorFirstName;
    private String doctorLastName;
    private String speciality;

    private String linkPhoto;
    private String about;

    public static DocDto fromDoctor(Doctor doctor) {


        DocDto docDto = new DocDto();
        docDto.setId(doctor.getId());
        docDto.setDoctorFirstName(doctor.getFirstName());
        docDto.setDoctorLastName(doctor.getLastName());
        docDto.setSpeciality(doctor.getSpeciality());
        docDto.setLinkPhoto(doctor.getPhoto());
        docDto.setAbout(doctor.getAbout());

        return docDto;
    }

    public static List<DocDto> fromDoctorList(List<Doctor> doctorList) {
        List<DocDto> doctorDtoList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            DocDto docDto = fromDoctor(doctor);
            doctorDtoList.add(docDto);
        }
        return doctorDtoList;
    }
}

