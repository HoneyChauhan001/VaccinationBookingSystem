package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.requestDTO.UserRequestDto;
import com.example.Dosify.dto.responseDTO.userInfoResponseDto;
import com.example.Dosify.dto.responseDTO.UpdateNameByMobNoDto;
import com.example.Dosify.dto.responseDTO.UserNameListDto;
import com.example.Dosify.dto.responseDTO.UserResponseDto;

public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public userInfoResponseDto getByEmail(String emailId);

    public UpdateNameByMobNoDto updateNameByMobNo(String mobNo, String name);

    UserNameListDto getUsersWithNoDoseTaken();

    UserNameListDto getUsersWithOnlyDose1Taken();

    UserNameListDto getUsersWithBothDoseTaken();

    UserNameListDto getMaleUsersWithNoDoseTaken();

    UserNameListDto getUserWithGenderAndDoseStatus(Gender gender, boolean isDose1Taken, boolean isDose2Taken);
}
