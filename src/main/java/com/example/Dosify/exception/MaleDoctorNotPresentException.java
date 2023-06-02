package com.example.Dosify.exception;

public class MaleDoctorNotPresentException extends Exception {
    public MaleDoctorNotPresentException(String noMaleDoctorPresent) {
        super(noMaleDoctorPresent);
    }
}
