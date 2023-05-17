package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose2;

public interface Dose2Service {
    Dose2 createDose(VaccineType vaccineType, int userId);
}
