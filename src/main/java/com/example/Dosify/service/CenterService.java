package com.example.Dosify.service;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;

import java.util.List;

public interface CenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

    List<DoctorResponseDto> getCenterDoctorList(int centerId) throws CenterNotExistException;
}
