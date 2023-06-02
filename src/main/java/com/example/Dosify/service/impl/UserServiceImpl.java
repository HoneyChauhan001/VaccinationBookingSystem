package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.requestDTO.UserRequestDto;
import com.example.Dosify.dto.responseDTO.userInfoResponseDto;
import com.example.Dosify.dto.responseDTO.UpdateNameByMobNoDto;
import com.example.Dosify.dto.responseDTO.UserNameListDto;
import com.example.Dosify.dto.responseDTO.UserResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.Dosify.Enum.Gender.MALE;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        //convert request Dto to entity
        User user = UserTransformer.userRequestDtoToUser(userRequestDto);

        //save the object in db
        User savedUser = userRepository.save(user);

        //convert entity to response dto
        UserResponseDto userResponseDto = UserTransformer.userToUserResponseDto(savedUser);
        return userResponseDto;
    }

    @Override
    public userInfoResponseDto getByEmail(String emailId) {
        User userByEmail = userRepository.findByEmailId(emailId);
        userInfoResponseDto userInfoResponseDto = UserTransformer.userToInfoResponseDto(userByEmail);

        return userInfoResponseDto;
    }

    @Override
    public UpdateNameByMobNoDto updateNameByMobNo(String mobNo, String name) {
        User user = userRepository.findByMobNo(mobNo);
        user.setName(name);
        userRepository.save(user);

        UpdateNameByMobNoDto updateNameByMobNoDto = new UpdateNameByMobNoDto();
        updateNameByMobNoDto.setMessage("Name Updated Successfully");
        return updateNameByMobNoDto;
    }

    @Override
    public UserNameListDto getUsersWithNoDoseTaken() {
        List<User> userList = userRepository.findByIsDose1TakenAndIsDose2Taken(false,false);

        return UserTransformer.userListToUserNameListDto(userList);
    }

    @Override
    public UserNameListDto getUsersWithOnlyDose1Taken() {
        List<User> userList = userRepository.findByIsDose1TakenAndIsDose2Taken(true,false);
        return UserTransformer.userListToUserNameListDto(userList);
    }

    @Override
    public UserNameListDto getUsersWithBothDoseTaken() {
        List<User> userList = userRepository.findByIsDose1TakenAndIsDose2Taken(true,true);
        return UserTransformer.userListToUserNameListDto(userList);
    }

    @Override
    public UserNameListDto getMaleUsersWithNoDoseTaken() {
        List<User> userList = userRepository.findByIsDose1TakenAndIsDose2TakenAndGender(false,false,MALE);
        return UserTransformer.userListToUserNameListDto(userList);
    }

    @Override
    public UserNameListDto getUserWithGenderAndDoseStatus(Gender gender, boolean isDose1Taken, boolean isDose2Taken) {
        List<User> userList = userRepository.findByIsDose1TakenAndIsDose2TakenAndGender(isDose1Taken,isDose2Taken,gender);
        return UserTransformer.userListToUserNameListDto(userList);
    }
}
