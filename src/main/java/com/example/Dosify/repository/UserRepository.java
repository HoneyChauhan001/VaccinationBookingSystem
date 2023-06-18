package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmailId(String emailId);

    User findByMobNo(String mobNo);

    List<User> findByIsDose1TakenAndIsDose2Taken(boolean isDose1Taken, boolean isDose2Taken);

    List<User> findByIsDose1TakenAndIsDose2TakenAndGender(boolean isDose1Taken, boolean isDose2Taken, Gender gender);


}
