package com.example.Dosify.controller;

import com.example.Dosify.dto.requestDTO.UserRequestDto;
import com.example.Dosify.dto.responseDTO.userInfoResponseDto;
import com.example.Dosify.dto.responseDTO.UpdateNameByMobNoDto;
import com.example.Dosify.dto.responseDTO.UserNameListDto;
import com.example.Dosify.dto.responseDTO.UserResponseDto;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-email")
    public ResponseEntity getByEmail(@RequestParam String emailId){
        userInfoResponseDto responseDto = userService.getByEmail(emailId);
        return new ResponseEntity(responseDto,HttpStatus.FOUND);
    }

    @PutMapping("/update-name-by-mobno")
    public ResponseEntity updateNameByMobNo(@RequestParam String mobNo, @RequestParam String name){
        UpdateNameByMobNoDto responseDto = userService.updateNameByMobNo(mobNo,name);
        return new ResponseEntity(responseDto,HttpStatus.OK);
    }

    @GetMapping("/user-with-no-dose-taken")
    public ResponseEntity getUsersWithNoDoseTaken(){
        UserNameListDto userList = userService.getUsersWithNoDoseTaken();
        return new ResponseEntity(userList,HttpStatus.OK);
    }

    @GetMapping("/users-with-only-dose1-taken")
    public ResponseEntity getUsersWithOnlyDose1Taken(){
        UserNameListDto userList = userService.getUsersWithOnlyDose1Taken();
        return new ResponseEntity(userList,HttpStatus.OK);
    }

    @GetMapping("/users-with-both-dose-taken")
    public ResponseEntity getUsersWithBothDoseTaken(){
        UserNameListDto userList = userService.getUsersWithBothDoseTaken();
        return new ResponseEntity(userList,HttpStatus.OK);
    }

    @GetMapping("/male-users-with-no-dose-taken")
    public ResponseEntity getMaleUsersWithNoDoseTaken(){
        UserNameListDto userList = userService.getMaleUsersWithNoDoseTaken();
        return new ResponseEntity(userList,HttpStatus.OK);
    }
}
