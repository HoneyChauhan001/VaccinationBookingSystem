package com.example.Dosify.transformer;

import com.example.Dosify.dto.requestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.responseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.model.Appointment;

import java.util.UUID;

public class AppointmentTransformer {
    public static Appointment dtoToAppointment(AppointmentRequestDto appointmentRequestDto) {
        return Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .build();
    }

    public static AppointmentResponseDto appointmentToResponseDto(Appointment appointment) {
        CenterResponseDto centerResponseDto = CenterTransformer.centerToResponseDto(appointment.getDoctor().getVaccinationCenter());
        String userName = appointment.getUser().getName();
        String doctorName = appointment.getDoctor().getName();

        return AppointmentResponseDto.builder()
                .userName(userName)
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .doctorName(doctorName)
                .centerDetails(centerResponseDto)
                .build();
    }
}
