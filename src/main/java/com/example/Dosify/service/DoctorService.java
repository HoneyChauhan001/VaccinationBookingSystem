package com.example.Dosify.service;

import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;

public interface DoctorService {
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotExistException;
}
