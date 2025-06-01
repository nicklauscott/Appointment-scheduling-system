package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.StaffNotFoundException;
import com.ams.tenant_service.exception.TenantNotFoundException;
import com.ams.tenant_service.exception.UpdatingStaffScheduleException;
import com.ams.tenant_service.model.dto.StaffRequestDTO;
import com.ams.tenant_service.model.dto.StaffResponseDTO;
import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.tenant_service.repository.StaffRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AdminService {


}
