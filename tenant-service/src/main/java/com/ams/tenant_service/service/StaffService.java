package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.StaffAlreadyExistException;
import com.ams.tenant_service.exception.StaffNotFoundException;
import com.ams.tenant_service.exception.TenantNotFoundException;
import com.ams.tenant_service.exception.UpdatingStaffScheduleException;
import com.ams.tenant_service.grpc.client.StaffGrpcServiceClient;
import com.ams.tenant_service.model.dto.staff.CustomScheduleDTO;
import com.ams.tenant_service.model.dto.staff.StaffRequestDTO;
import com.ams.tenant_service.model.dto.staff.StaffResponseDTO;
import com.ams.tenant_service.model.dto.staff.WeeklyScheduleDTO;
import com.ams.tenant_service.model.entities.tenant.CustomSchedule;
import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.model.entities.tenant.WeeklySchedule;
import com.ams.tenant_service.model.mapper.StaffMapper;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.tenant_service.repository.CustomScheduleRepository;
import com.ams.tenant_service.repository.StaffRepository;
import com.ams.tenant_service.repository.WeeklyScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StaffService {

    private final StaffRepository repository;
    private final WeeklyScheduleRepository weeklyScheduleRepository;
    private final CustomScheduleRepository customScheduleRepository;
    private final StaffGrpcServiceClient grpcClient;

    public String createStaff(String email) {
        Optional<Staff> optional = repository.findByEmail(email);
        if (optional.isPresent())
            throw new StaffAlreadyExistException("A staff with this email " + email + " already exist");
        Staff staff = new Staff();
        staff.setEmail(email);
        staff.setFirstName("");
        staff.setLastName("");
        return repository.save(staff).getEmail();
    }

    public StaffResponseDTO getStaffProfile() {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            return StaffMapper.toDTO(staff);
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        }
    }

    public StaffResponseDTO updateStaffProfile(StaffRequestDTO request) {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));

            staff.setFirstName(request.getFirstName());
            staff.setLastName(request.getLastName());
            if (!request.getGender().isBlank()) staff.setGender(request.getGender());
            if (!request.getMobile().isBlank()) staff.setMobile(request.getMobile());
            if (!request.getAddress().isBlank()) staff.setAddress(request.getAddress());
            if (!request.getProfilePictureUrl().isBlank()) staff.setProfilePictureUrl(request.getProfilePictureUrl());
            if (request.getDateOfBirth() != null) staff.setDateOfBirth(request.getDateOfBirth());
            repository.save(staff);

            Staff grpcStaff = new Staff();
            grpcStaff.setEmail(staffEmail);
            grpcStaff.setFirstName(staff.getFirstName());
            grpcStaff.setWeeklySchedule(Collections.emptyList());
            grpcStaff.setCustomSchedules(Collections.emptyList());
            grpcClient.updateStaffSchedule(grpcStaff);

            return StaffMapper.toDTO(staff);
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        } catch (Exception e) {
            log.info("Error occurred while updating staff snapshot. {}", e.getMessage());
            throw new UpdatingStaffScheduleException("Couldn't update staff snapshot");
        }
    }

    public List<WeeklyScheduleDTO> getWeekSchedule() {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            return staff.getWeeklySchedule().stream().map(StaffMapper::toDTO).toList();
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        }
    }

    public WeeklyScheduleDTO addWeeklySchedule(WeeklyScheduleDTO request) {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            WeeklySchedule schedule = StaffMapper.toEntity(staff, request);
            WeeklySchedule updatedSchedule = weeklyScheduleRepository.save(schedule);
            Staff updatedStaff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            grpcClient.updateStaffSchedule(updatedStaff);
            return StaffMapper.toDTO(updatedSchedule);
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        }
    }

    public List<CustomScheduleDTO> getCustomSchedules() {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            return staff.getCustomSchedules().stream().map(StaffMapper::toDTO).toList();
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        }
    }

    public CustomScheduleDTO addCustomSchedule(CustomScheduleDTO request) {
        String staffEmail = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
        try {
            Staff staff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            CustomSchedule schedule = StaffMapper.toEntity(staff, request);
            CustomSchedule updatedSchedule = customScheduleRepository.save(schedule);
            Staff updatedStaff = repository.findByEmail(staffEmail)
                    .orElseThrow(() -> new StaffNotFoundException("No staff with the id: " + staffEmail));
            grpcClient.updateStaffSchedule(updatedStaff);
            return StaffMapper.toDTO(updatedSchedule);
        } catch (IllegalArgumentException e) {
            throw new StaffNotFoundException("No staff with the id: " + staffEmail);
        } catch (DataIntegrityViolationException e) {
            throw new UpdatingStaffScheduleException("Staff schedule with the same data already exist");
        } catch (DataAccessException e) {
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
        }
    }

}
