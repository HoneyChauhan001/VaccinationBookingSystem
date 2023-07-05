package com.example.Dosify.controller;

import com.example.Dosify.dto.requestDTO.CenterRequestDto;
import com.example.Dosify.dto.responseDTO.CenterResponseDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class CenterController {
    @Autowired
    CenterService centerService;

    @PostMapping("/add")
    public ResponseEntity addCenter(@RequestBody CenterRequestDto centerRequestDto){

        CenterResponseDto centerResponseDto = centerService.addCenter(centerRequestDto);
        return new ResponseEntity(centerResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/get-doctors-of-center/{centerId}")
    public ResponseEntity getCenterDoctorList(@PathVariable int centerId){
        try{
            List<DoctorResponseDto> doctorList = centerService.getCenterDoctorList(centerId);
            return new ResponseEntity(doctorList,HttpStatus.FOUND);
        } catch (CenterNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
