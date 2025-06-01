package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.dto.*;
import com.ams.tenant_service.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService service;

    // ---------------------------  Remove later  --------------------------------------------

    @GetMapping("/test")
    public void test() {
        service.updateAppointmentServiceViaGrpc();
    }


    // ----------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<StaffResponseDTO> getStaffProfile() {
        return ResponseEntity.ok(service.getStaffProfile());
    }

    @PatchMapping
    public ResponseEntity<StaffResponseDTO> updateStaffProfile(@RequestBody StaffRequestDTO request) {
        return ResponseEntity.ok(service.updateStaffProfile(request));
    }

    @GetMapping("/weekly_schedule")
    public ResponseEntity<List<WeeklyScheduleDTO>> getWeekSchedule() {
        return ResponseEntity.ok(service.getWeekSchedule());
    }

    @PostMapping("/weekly_schedule")
    public ResponseEntity<WeeklyScheduleDTO> addWeeklySchedule(@RequestBody WeeklyScheduleDTO request) {
        return ResponseEntity.ok(service.addWeeklySchedule(request));
    }

    @GetMapping("/custom_schedule")
    public ResponseEntity<List<CustomScheduleDTO>> getCustomSchedules() {
        return ResponseEntity.ok(service.getCustomSchedules());
    }

    @PostMapping("/custom_schedule")
    public ResponseEntity<CustomScheduleDTO> addCustomSchedule(@RequestBody CustomScheduleDTO request) {
        return ResponseEntity.ok(service.addCustomSchedule(request));
    }

}
