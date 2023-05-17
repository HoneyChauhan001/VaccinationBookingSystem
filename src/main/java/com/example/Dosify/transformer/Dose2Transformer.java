package com.example.Dosify.transformer;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;

import java.util.UUID;

public class Dose2Transformer {
    public static Dose2 dtoToDose2(VaccineType vaccineType, User user) {
        return Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
