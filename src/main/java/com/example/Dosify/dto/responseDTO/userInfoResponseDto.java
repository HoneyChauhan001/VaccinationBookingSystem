package com.example.Dosify.dto.responseDTO;

import com.example.Dosify.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class userInfoResponseDto {
    String name;
    int age;
    String emailId;
    String mobNo;
    Gender gender;
    boolean isDose1Taken;
    boolean isDose2Taken;
}
