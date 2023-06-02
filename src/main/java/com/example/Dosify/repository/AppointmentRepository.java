package com.example.Dosify.repository;

import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query(value = "Select doctor from appointment group by doctor having count(*) > :numberOfAppointments",nativeQuery = true)
    List<Integer> findDoctorWithAppointmentsGreaterThan(int numberOfAppointments);
}
