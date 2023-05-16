package com.example.Dosify.transformer;

import com.example.Dosify.dto.requestDTO.UserRequestDto;
import com.example.Dosify.dto.responseDTO.UserNameListDto;
import com.example.Dosify.dto.responseDTO.userInfoResponseDto;
import com.example.Dosify.dto.responseDTO.UserResponseDto;
import com.example.Dosify.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {
    public static User userRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .mobNo(userRequestDto.getMobNo())
                .gender(userRequestDto.getGender())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats!!! you have registered on Dosify")
                .build();
    }

    public static userInfoResponseDto userToInfoResponseDto(User user){
        return userInfoResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .emailId(user.getEmailId())
                .mobNo(user.getMobNo())
                .gender(user.getGender())
                .isDose1Taken(user.isDose1Taken())
                .isDose2Taken(user.isDose2Taken())
                .build();
    }

    public static UserNameListDto userListToUserNameListDto(List<User> userList){
        List<String> names = new ArrayList<>();
        for(User user : userList){
            names.add(user.getName());
        }
        return UserNameListDto.builder()
                .namesOfUser(names)
                .build();
    }
}
