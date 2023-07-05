package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.exception.FemaleDoctorNotPresentException;
import com.example.Dosify.exception.MaleDoctorNotPresentException;

import java.util.List;

public interface DoctorService {
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotExistException;

    List<DoctorResponseDto> GetDoctorWithAppointmentMoreThan(int numberOfAppointments) throws DoctorNotExistException;

    List<DoctorResponseDto> getDoctorWithAgeMoreThan(int age) throws DoctorNotExistException;

    List<DoctorResponseDto> getDoctorWithGenderAndAgeMoreThan(Gender gender, int age) throws DoctorNotExistException;

    double getRatioOfFemaleToMale() throws MaleDoctorNotPresentException, FemaleDoctorNotPresentException;

    void updateDoctorCenter(String emailId, int centerId) throws DoctorNotExistException, CenterNotExistException;
}
