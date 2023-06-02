package com.example.Dosify.exception;

public class FemaleDoctorNotPresentException extends Exception {
    public FemaleDoctorNotPresentException(String noFemaleDoctorPresent) {
        super(noFemaleDoctorPresent);
    }
}
