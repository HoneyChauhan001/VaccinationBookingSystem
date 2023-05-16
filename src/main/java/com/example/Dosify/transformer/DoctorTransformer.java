package com.example.Dosify.transformer;

import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorTransformer {
    @Autowired
    CenterRepository centerRepository;
    public static Doctor doctorRequestDtotoDoctor(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .emailId(doctorRequestDto.getEmailId())
                .mobNo(doctorRequestDto.getMobNo())
                .gender(doctorRequestDto.getGender())
                .build();
    }

    public static DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto = CenterTransformer.centerToResponseDto(doctor.getVaccinationCenter());
        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .mobNo(doctor.getMobNo())
                .emailId(doctor.getEmailId())
                .centerDetails(centerResponseDto)
                .build();
    }
}
