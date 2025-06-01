package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.dto.CustomScheduleDTO;
import com.ams.tenant_service.model.dto.StaffRequestDTO;
import com.ams.tenant_service.model.dto.StaffResponseDTO;
import com.ams.tenant_service.model.dto.WeeklyScheduleDTO;
import com.ams.tenant_service.service.StaffService;
import com.ams.tenant_service.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {

    private final TenantService service;

    @GetMapping("/test")
    public void test() {
        service.updateTenant();
    }

}
