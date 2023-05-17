package com.example.Dosify.service;

import com.example.Dosify.dto.requestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.responseDTO.AppointmentResponseDto;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.exception.Dose1NotTakenException;
import com.example.Dosify.exception.DoseAlreadyTakenException;
import com.example.Dosify.exception.UserNotExistException;

public interface AppointmentService {

    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotExistException, DoctorNotExistException, DoseAlreadyTakenException, Dose1NotTakenException;
}
