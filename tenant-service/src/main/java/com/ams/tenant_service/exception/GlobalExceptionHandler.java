package com.ams.tenant_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StaffNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleStaffNotFoundException(StaffNotFoundException e) {
        System.out.println("Handling StaffNotFoundException");
        Map<String, String> error = Map.of("message", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



    // TenantNotFoundException
    @ExceptionHandler(TenantNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleTenantNotFoundException(TenantNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AppointmentOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleAppointmentOperationException(AppointmentOperationException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return  ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UpdatingStaffScheduleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleUpdatingStaffScheduleException(UpdatingStaffScheduleException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

}
