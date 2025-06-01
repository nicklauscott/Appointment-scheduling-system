package com.ams.tenant_service.exception;

public class StaffAlreadyExistException extends RuntimeException {
    public StaffAlreadyExistException(String message) {
        super(message);
    }
}