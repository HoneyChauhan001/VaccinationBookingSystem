package com.example.Dosify.transformer;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;

import java.util.UUID;

public class Dose1Transformer {
    public static Dose1 dtoToDose1(VaccineType vaccineType, User user) {
        return Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
