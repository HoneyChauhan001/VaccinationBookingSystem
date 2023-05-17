package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose1;

public interface Dose1Service {
    Dose1 createDose(VaccineType vaccineType, int userId);
}
