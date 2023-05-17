package com.example.Dosify.controller;

import com.example.Dosify.dto.requestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.responseDTO.AppointmentResponseDto;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.exception.Dose1NotTakenException;
import com.example.Dosify.exception.DoseAlreadyTakenException;
import com.example.Dosify.exception.UserNotExistException;
import com.example.Dosify.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
        try{
            AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity(appointmentResponseDto, HttpStatus.CREATED);
        } catch (DoctorNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (DoseAlreadyTakenException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (UserNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Dose1NotTakenException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
