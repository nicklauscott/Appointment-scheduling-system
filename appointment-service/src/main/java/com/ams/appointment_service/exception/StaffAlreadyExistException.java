package com.ams.appointment_service.exception;

public class StaffAlreadyExistException extends RuntimeException {
    public StaffAlreadyExistException(String message) {
        super(message);
    }
}
