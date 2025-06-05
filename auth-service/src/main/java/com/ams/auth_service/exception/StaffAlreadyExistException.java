package com.ams.auth_service.exception;

public class StaffAlreadyExistException extends RuntimeException {
    public StaffAlreadyExistException(String message) {
        super(message);
    }
}