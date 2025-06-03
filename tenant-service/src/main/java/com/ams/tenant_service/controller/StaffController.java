package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.dto.staff.CustomScheduleDTO;
import com.ams.tenant_service.model.dto.staff.StaffRequestDTO;
import com.ams.tenant_service.model.dto.staff.StaffResponseDTO;
import com.ams.tenant_service.model.dto.staff.WeeklyScheduleDTO;
import com.ams.tenant_service.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/staff")
@Tag(name = "Staff", description = "Api for managing staffs")
@AllArgsConstructor
public class StaffController {

    private final StaffService service;

    @GetMapping
    @Operation(summary = "Get a staff profile")
    public ResponseEntity<StaffResponseDTO> getStaffProfile() {
        return ResponseEntity.ok(service.getStaffProfile());
    }

    @PatchMapping
    @Operation(summary = "Update a staff profile")
    public ResponseEntity<StaffResponseDTO> updateStaffProfile(@RequestBody StaffRequestDTO request) {
        return ResponseEntity.ok(service.updateStaffProfile(request));
    }

    @GetMapping("/weekly_schedule")
    @Operation(summary = "Get a staff weekly schedule")
    public ResponseEntity<List<WeeklyScheduleDTO>> getWeekSchedule() {
        return ResponseEntity.ok(service.getWeekSchedule());
    }

    @PostMapping("/weekly_schedule")
    @Operation(summary = "Update a staff weekly schedule")
    public ResponseEntity<WeeklyScheduleDTO> addWeeklySchedule(@RequestBody WeeklyScheduleDTO request) {
        return ResponseEntity.ok(service.addWeeklySchedule(request));
    }

    @GetMapping("/custom_schedule")
    @Operation(summary = "Get a staff custom schedule")
    public ResponseEntity<List<CustomScheduleDTO>> getCustomSchedules() {
        return ResponseEntity.ok(service.getCustomSchedules());
    }

    @PostMapping("/custom_schedule")
    @Operation(summary = "Update a staff custom schedule")
    public ResponseEntity<CustomScheduleDTO> addCustomSchedule(@RequestBody CustomScheduleDTO request) {
        return ResponseEntity.ok(service.addCustomSchedule(request));
    }

}
