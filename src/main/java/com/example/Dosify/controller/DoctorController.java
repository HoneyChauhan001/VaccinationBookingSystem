package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto,HttpStatus.CREATED);
        } catch (CenterNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/appointment-more-than/{numberOfAppointments}")
    public ResponseEntity GetDoctorWithAppointmentMoreThan(@PathVariable int numberOfAppointments){
        try{
            List<DoctorResponseDto> doctorResponseDtoList = doctorService.GetDoctorWithAppointmentMoreThan(numberOfAppointments);
            return new ResponseEntity(doctorResponseDtoList,HttpStatus.OK);
        }catch (DoctorNotExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/age-more-than/{age}")
    public ResponseEntity getDoctorWithAgeMoreThan(@PathVariable int age){
        try{
            List<DoctorResponseDto> doctorResponseDtoList = doctorService.getDoctorWithAgeMoreThan(age);
            return new ResponseEntity(doctorResponseDtoList,HttpStatus.OK);
        }catch (DoctorNotExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/gender/{gender}/age-more-than/{age}")
    public ResponseEntity getDoctorWithGenderAndAgeMoreThan(@PathVariable Gender gender, @PathVariable int age){
        try{
            List<DoctorResponseDto> doctorResponseDtoList = doctorService.getDoctorWithGenderAndAgeMoreThan(gender,age);
            return new ResponseEntity(doctorResponseDtoList,HttpStatus.OK);
        }catch (DoctorNotExistException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/female-to-male-ration")
    public ResponseEntity getRatioOfFemaleToMale(){
        try{
            double ratio = doctorService.getRatioOfFemaleToMale();
            return new ResponseEntity(ratio,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }
}
