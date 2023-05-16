package com.example.Dosify.model;

import com.example.Dosify.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "dose_1")
public class Dose1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "dose_id")
    String doseId;
    @Column(name = "vaccine_type")
    @Enumerated(EnumType.STRING)
    VaccineType vaccineType;
    @Column(name = "vaccination_date")
    @CreationTimestamp
    Date vaccinationDate;
    @OneToOne
    @JoinColumn(name = "user")
    User user;

}
