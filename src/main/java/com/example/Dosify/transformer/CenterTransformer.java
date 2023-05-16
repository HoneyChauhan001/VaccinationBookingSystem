package com.example.Dosify.transformer;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;

public class CenterTransformer {
    public static VaccinationCenter centerRequestDtotoCenter(CenterRequestDto centerRequestDto){
        return VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .location((centerRequestDto.getLocation()))
                .centerType(centerRequestDto.getCenterType())
                .build();

    }

    public static CenterResponseDto centerToResponseDto(VaccinationCenter vaccinationCenter){
        return CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }
}
