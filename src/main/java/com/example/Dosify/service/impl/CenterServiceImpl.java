package com.example.Dosify.service.impl;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.service.CenterService;
import com.example.Dosify.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
