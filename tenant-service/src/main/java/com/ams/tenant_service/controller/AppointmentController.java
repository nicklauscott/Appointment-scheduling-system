package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.constant.AppointmentRequestDTO;
import com.ams.tenant_service.model.constant.AppointmentResponseDTO;
import com.ams.tenant_service.model.constant.OnConfirm;
import com.ams.tenant_service.model.constant.OnDelete;
import com.ams.tenant_service.model.dto.AppointmentActionRequestDTO;
import com.ams.tenant_service.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping("confirm_appointment")
    public ResponseEntity<String> confirmAppointment(
            @Validated(OnConfirm.class) @RequestBody
            AppointmentActionRequestDTO request
    ) {
        return ResponseEntity.ok(service.confirmAppointment(request));
    }

    @PostMapping("confirm_booked_appointment")
    public ResponseEntity<String> confirmAppointmentRescheduled(
            @Validated(OnConfirm.class) @RequestBody
            AppointmentActionRequestDTO request
    ) {
        return ResponseEntity.ok(service.confirmAppointmentRescheduled(request));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAppointment(
            @Validated(OnDelete.class) @RequestBody
            AppointmentActionRequestDTO request
    ) {
        return ResponseEntity.ok(service.deleteAppointment(request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsForStaff() {
        return ResponseEntity.ok(service.getAllAppointmentsForStaff());
    }

    @PatchMapping
    public ResponseEntity<AppointmentRequestDTO> editAppointment(
            @RequestBody AppointmentRequestDTO appointment
    ) {
        return ResponseEntity.ok(service.editAppointment(appointment));
    }

}
