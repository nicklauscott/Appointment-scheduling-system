package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.constant.AppointmentRequestDTO;
import com.ams.tenant_service.model.constant.AppointmentResponseDTO;
import com.ams.tenant_service.model.dto.AppointmentActionRequestDTO;
import com.ams.tenant_service.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@Tag(name = "Appointment", description = "Api for staff appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping("confirm_appointment")
    @Operation(summary = "Confirm a booked appointment")
    public ResponseEntity<String> confirmAppointment(
            @Valid @RequestBody AppointmentActionRequestDTO request
    ) {
        return ResponseEntity.ok(service.confirmAppointment(request));
    }

    @PostMapping("confirm_booked_appointment")
    @Operation(summary = "Confirm a rescheduled appointment")
    public ResponseEntity<String> confirmAppointmentRescheduled(
            @Valid @RequestBody AppointmentActionRequestDTO request
    ) {
        return ResponseEntity.ok(service.confirmAppointmentRescheduled(request));
    }

    @DeleteMapping("/{appointment_id}")
    @Operation(summary = "Cancel/Delete an appointment")
    public ResponseEntity<String> deleteAppointment(@PathVariable("appointment_id") String appointmentId) {
        return ResponseEntity.ok(service.deleteAppointment(appointmentId));
    }

    @GetMapping
    @Operation(summary = "Get all appointments for a staff")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsForStaff() {
        return ResponseEntity.ok(service.getAllAppointmentsForStaff());
    }

    @PatchMapping
    @Operation(summary = "Edit an appointment")
    public ResponseEntity<AppointmentRequestDTO> editAppointment(
            @RequestBody AppointmentRequestDTO appointment
    ) {
        return ResponseEntity.ok(service.editAppointment(appointment));
    }

}