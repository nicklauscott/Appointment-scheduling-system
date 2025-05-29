package com.ams.appointment_service.exception;

public class NoStaffAvailableException extends RuntimeException {
    public NoStaffAvailableException(String message) {
        super(message);
    }
}
