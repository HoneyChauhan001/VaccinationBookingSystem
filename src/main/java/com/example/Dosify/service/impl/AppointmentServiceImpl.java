package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.dto.requestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.responseDTO.AppointmentResponseDto;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.exception.Dose1NotTakenException;
import com.example.Dosify.exception.DoseAlreadyTakenException;
import com.example.Dosify.exception.UserNotExistException;
import com.example.Dosify.model.*;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.service.Dose2Service;
import com.example.Dosify.transformer.AppointmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    Dose1Service dose1Service;
    @Autowired
    Dose2Service dose2Service;
    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotExistException, DoctorNotExistException, DoseAlreadyTakenException, Dose1NotTakenException {
        Optional<User> userOpt =  userRepository.findById(appointmentRequestDto.getUserId());
        if(userOpt.isEmpty()){
            throw new UserNotExistException("user not present in database");
        }
        Optional<Doctor> doctorOpt = doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(userOpt.isEmpty()){
            throw new DoctorNotExistException("doctor not present in database");
        }
        User user = userOpt.get();
        Doctor doctor = doctorOpt.get();

        if(appointmentRequestDto.getDoseNo() == DoseNo.DOSE_1){
            if(user.isDose1Taken()==true){
                throw new DoseAlreadyTakenException("dose 1 already taken by user");
            }
            Dose1 dose1 = dose1Service.createDose(appointmentRequestDto.getVaccineType(),user);
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }
        else {
            if(user.isDose1Taken()==false){
                throw new Dose1NotTakenException("dose 1 not taken by user, first take dose 1 to take dose 2");
            }
            if(user.isDose2Taken()==true){
                throw new DoseAlreadyTakenException("dose 2 already taken by user");
            }
            Dose2 dose2 = dose2Service.createDose(appointmentRequestDto.getVaccineType(),user);
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }

        Appointment appointment = AppointmentTransformer.dtoToAppointment(appointmentRequestDto);
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        user.getAppointments().add(appointment);
        User savedUser = userRepository.save(user);

        List<Appointment> appointmentList = savedUser.getAppointments();
        int size = appointmentList.size();
        Appointment savedAppointment = appointmentList.get(size-1); // to send saved user with date to Dto
        doctor.getAppointments().add(savedAppointment);

        doctorRepository.save(doctor);

        return AppointmentTransformer.appointmentToResponseDto(savedAppointment);

    }
}
