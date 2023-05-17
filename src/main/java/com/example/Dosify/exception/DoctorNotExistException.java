package com.example.Dosify.exception;

public class DoctorNotExistException extends Throwable {
    public DoctorNotExistException(String doctorNotPresentInDatabase) {
        super(doctorNotPresentInDatabase);
    }
}
