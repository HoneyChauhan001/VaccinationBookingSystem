package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.Dose2Service;
import com.example.Dosify.transformer.Dose1Transformer;
import com.example.Dosify.transformer.Dose2Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Dose2ServiceImpl implements Dose2Service {
    @Autowired
    UserRepository userRepository;
    @Override
    public Dose2 createDose(VaccineType vaccineType, int userId) {
        Optional<User> userOpt =  userRepository.findById(userId);
        //create dose
        Dose2 dose2 = Dose2Transformer.dtoToDose2(vaccineType,userOpt.get());
        User user = userOpt.get();
        user.setDose2Taken(true);
        user.setDose2(dose2);
        return dose2;
    }
}
