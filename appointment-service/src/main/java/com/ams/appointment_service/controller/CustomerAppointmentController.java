package com.ams.appointment_service.controller;

import com.ams.appointment_service.dto.*;
import com.ams.appointment_service.service.CustomerAppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class CustomerAppointmentController {

    private final CustomerAppointmentService service;
    private final DataSource dataSource;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> book(@Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO response = service.bookAppointment(request);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<String> reschedule(
            @Valid @RequestBody RescheduleRequestDTO request
    ) {
        service.reschedule(request.getId(), request);
        return ResponseEntity.ok("You'll receive an email when your request has been approved");
    }

    @PostMapping("/confirm_reschedule")
    public ResponseEntity<AppointmentResponseDTO> confirmReschedule(
            @Valid @RequestBody ConfirmRescheduleRequestDTO request
    ) {
        AppointmentResponseDTO response = service.confirmReschedule(request.getId(), request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> cancel(
            @Valid @RequestBody CancelRequestDTO request
    ) {
        service.cancelAppointment(request.getId(), request.getEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
