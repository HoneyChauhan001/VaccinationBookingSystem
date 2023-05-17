package com.example.Dosify.dto.responseDTO;

import com.example.Dosify.Enum.DoseNo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {
    String userName;
    String appointmentNo;
    Date dateOfAppointment;
    DoseNo doseNo;
    String doctorName;
    CenterResponseDto centerDetails;
}
