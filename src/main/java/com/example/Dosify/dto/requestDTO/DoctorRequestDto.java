package com.example.Dosify.dto.requestDTO;

import com.example.Dosify.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequestDto {
    String name;
    int age;
    String emailId;
    String mobNo;
    Gender gender;
    int centerId;

}
