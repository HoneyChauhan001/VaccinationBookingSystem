package com.example.Dosify.exception;

public class UserNotExistException extends Throwable {
    public UserNotExistException(String userNotPresentInDatabase) {
        super(userNotPresentInDatabase);
    }
}
