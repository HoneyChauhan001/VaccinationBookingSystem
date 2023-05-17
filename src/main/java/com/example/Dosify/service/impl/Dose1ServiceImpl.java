package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.transformer.Dose1Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Dose1ServiceImpl implements Dose1Service {
    @Autowired
    UserRepository userRepository;
    @Override
    public Dose1 createDose(VaccineType vaccineType, int userId) {
        Optional<User> userOpt =  userRepository.findById(userId);
        //create dose
        Dose1 dose1 = Dose1Transformer.dtoToDose1(vaccineType,userOpt.get());
        User user = userOpt.get();
        user.setDose1Taken(true);
        user.setDose1(dose1);
        return dose1;
    }
}
