package com.ams.auth_service.exception;

public class TenantAlreadyExistException extends RuntimeException {
    public TenantAlreadyExistException(String message) {
        super(message);
    }
}