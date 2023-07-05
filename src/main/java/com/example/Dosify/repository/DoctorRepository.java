package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query(value = "Select * from doctor where age > :age", nativeQuery = true)
    List<Doctor> findByAgeMoreThan(int age);

    @Query(value = "Select * from doctor where gender = :gender and age > :age", nativeQuery = true)
    List<Doctor> getDoctorWithGenderAndAgeMoreThan(String gender, int age);

    List<Doctor> findByGender(Gender gender);

    Doctor findByEmailId(String emailId);
}
