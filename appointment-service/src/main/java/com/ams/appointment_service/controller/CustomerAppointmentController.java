package com.ams.appointment_service.controller;

import com.ams.appointment_service.dto.*;
import com.ams.appointment_service.service.CustomerAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@Tag(name = "Appointment", description = "Api for customer appointments")
@AllArgsConstructor
public class CustomerAppointmentController {

    private final CustomerAppointmentService service;

    @GetMapping
    @Operation(summary = "Get a booked appointment")
    public ResponseEntity<AppointmentResponseDTO> getAppointment(@Valid @RequestBody AppointmentActionDTO request) {
        AppointmentResponseDTO response = service.getAppointment (request);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Booked an appointment")
    public ResponseEntity<AppointmentResponseDTO> book(@Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO response = service.bookAppointment(request);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    @Operation(summary = "Rescheduled an appointment")
    public ResponseEntity<String> reschedule(
            @Valid @RequestBody RescheduleRequestDTO request
    ) {
        service.reschedule(request.getId(), request);
        return ResponseEntity.ok("You'll receive an email when your request has been approved");
    }

    @PostMapping("/confirm_reschedule")
    @Operation(summary = "Confirm a rescheduled appointment")
    public ResponseEntity<AppointmentResponseDTO> confirmReschedule(
            @Valid @RequestBody ConfirmRescheduleRequestDTO request
    ) {
        AppointmentResponseDTO response = service.confirmReschedule(request.getId(), request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Cancel/Delete an appointment")
    public ResponseEntity<String> cancel(
            @Valid @RequestBody CancelRequestDTO request
    ) {
        service.cancelAppointment(request.getId(), request.getEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
