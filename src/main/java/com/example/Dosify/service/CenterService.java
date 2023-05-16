package com.example.Dosify.service;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;

public interface CenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);
}
