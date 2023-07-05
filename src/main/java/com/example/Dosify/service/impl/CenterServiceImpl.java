package com.example.Dosify.service.impl;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.service.CenterService;
import com.example.Dosify.transformer.CenterTransformer;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;
    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {
        //Dto to center
        VaccinationCenter vaccinationCenter = CenterTransformer.centerRequestDtotoCenter(centerRequestDto);

        //save in repo
        VaccinationCenter savedCenter =  centerRepository.save(vaccinationCenter);

        //center to responseDto
        return CenterTransformer.centerToResponseDto(savedCenter);

    }

    @Override
    public List<DoctorResponseDto> getCenterDoctorList(int centerId) throws CenterNotExistException {

        Optional<VaccinationCenter> centerOpt = centerRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new CenterNotExistException("No center with following id");
        }

        VaccinationCenter center = centerOpt.get();
        List<Doctor> doctorList = center.getDoctors();
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            DoctorResponseDto doctorResponseDto = DoctorTransformer.doctorToDoctorResponseDto(doctor);
            doctorResponseDtoList.add(doctorResponseDto);
        }
        return doctorResponseDtoList;
    }
}
