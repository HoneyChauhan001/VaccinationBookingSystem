package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.requestDTO.DoctorRequestDto;
import com.example.Dosify.dto.responseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotExistException;
import com.example.Dosify.exception.DoctorNotExistException;
import com.example.Dosify.exception.FemaleDoctorNotPresentException;
import com.example.Dosify.exception.MaleDoctorNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    CenterRepository centerRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotExistException {
        Optional<VaccinationCenter> centerOpt = centerRepository.findById(doctorRequestDto.getCenterId());

        if(centerOpt.isEmpty()){
            throw new CenterNotExistException("Invalid center Id");
        }

        VaccinationCenter center = centerOpt.get();

        //Dto to doctor
        Doctor doctor = DoctorTransformer.doctorRequestDtotoDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);
        center.getDoctors().add(doctor);

        //save -> saving center will save both center and doctor
        centerRepository.save(center);

        //doctor to Response dto
        return DoctorTransformer.doctorToDoctorResponseDto(doctor);



    }

    @Override
    public List<DoctorResponseDto> GetDoctorWithAppointmentMoreThan(int numberOfAppointments) throws DoctorNotExistException {
//        List<Doctor> doctorList = doctorRepository.findAll();
//        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
//        for(Doctor doctor : doctorList){
//            if(doctor.getAppointments().size()>numberOfAppointments){
//                doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
//            }
//        }
//        if(doctorResponseDtoList.isEmpty()){
//            throw new DoctorNotExistException("No Doctor with appointments more than " + numberOfAppointments);
//        }
//        return doctorResponseDtoList;
        List<Integer> doctorList = appointmentRepository.findDoctorWithAppointmentsGreaterThan(numberOfAppointments);
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for (Integer id : doctorList) {
            Doctor doctor = doctorRepository.findById(id).get();
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        if(doctorResponseDtoList.isEmpty()){
            throw new DoctorNotExistException("No Doctor with appointments more than " + numberOfAppointments);
        }
        return doctorResponseDtoList;

    }

    @Override
    public List<DoctorResponseDto> getDoctorWithAgeMoreThan(int age) throws DoctorNotExistException {

        List<Doctor> doctorList = doctorRepository.findByAgeMoreThan(age);
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        if(doctorResponseDtoList.isEmpty()){
            throw new DoctorNotExistException("No Doctor with age more then " + age);
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getDoctorWithGenderAndAgeMoreThan(Gender gender, int age) throws DoctorNotExistException {
        List<Doctor> doctorList = doctorRepository.getDoctorWithGenderAndAgeMoreThan(gender.toString(),age);
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        if(doctorResponseDtoList.isEmpty()){
            throw new DoctorNotExistException("No Doctor with gender " + gender.toString() + " more then " + age);
        }
        return doctorResponseDtoList;
    }

    @Override
    public double getRatioOfFemaleToMale() throws MaleDoctorNotPresentException, FemaleDoctorNotPresentException {
        int males = doctorRepository.findByGender(Gender.MALE).size();
        int females = doctorRepository.findByGender(Gender.FEMALE).size();

        if(males == 0){
            throw new MaleDoctorNotPresentException("No Male doctor present");
        }
        if(females == 0){
            throw new FemaleDoctorNotPresentException("No Female doctor present");
        }
        return (females * 1.0)/males;
    }

    @Override
    public void updateDoctorCenter(String emailId, int centerId) throws DoctorNotExistException, CenterNotExistException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
        if(doctor == null){
            throw new DoctorNotExistException("No doctor with following emailId");
        }
        Optional<VaccinationCenter> centerOpt = centerRepository.findById(centerId);
        if(centerOpt.isEmpty()){
            throw new CenterNotExistException("No center with following center ID");
        }

        VaccinationCenter center = centerOpt.get();

        doctor.getVaccinationCenter().getDoctors().remove(doctor);

        doctor.setVaccinationCenter(center);
        center.getDoctors().add(doctor);

        centerRepository.save(center);

        return;
    }


}
