package com.example.Dosify.service.impl;

import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    CenterRepository centerRepository;
    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotExistException {
        Optional<VaccinationCenter> centerOpt = centerRepository.findById(doctorRequestDto.getCenterId());

        if(centerOpt.isEmpty()){
            throw new CenterNotExistException("Invalid center Id");
        }

        VaccinationCenter center = centerOpt.get();

        //Dto to doctor
        Doctor doctor = DoctorTransformer.doctorRequestDtotoDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);
        center.getDoctors().add(doctor);

        //save -> saving center will save both center and doctor
        centerRepository.save(center);

        //doctor to Response dto
        return DoctorTransformer.doctorToDoctorResponseDto(doctor);



    }
}
