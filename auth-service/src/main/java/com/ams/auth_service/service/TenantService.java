package com.ams.auth_service.service;

import com.ams.auth_service.dto.*;
import com.ams.auth_service.exception.BadCredentialsException;
import com.ams.auth_service.exception.StaffAlreadyExistException;
import com.ams.auth_service.exception.TenantAlreadyExistException;
import com.ams.auth_service.model.Staff;
import com.ams.auth_service.model.Tenant;
import com.ams.auth_service.repository.StaffRepository;
import com.ams.auth_service.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TenantService {

    private TenantRepository repository;
    private StaffRepository staffRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public TokenPairResponseDTO login(LoginRequestDTO request) {
        Tenant tenant = repository.findByIdAndEmail(request.getTenantId(), request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials! check your tenant id, email and password"));

        if (!passwordEncoder.matches(request.getPassword(), tenant.getHashedPassword())) {
            throw new BadCredentialsException("Invalid credentials! check your tenant id, email and password");
        }

        return jwtService.generateTokenPair(tenant.getId(), tenant.getEmail(), "ADMIN");
    }

    public TokenPairResponseDTO refresh(RefreshTokenRequestDTO request) {


        return jwtService.generateTokenPair("", "", "");
    }

    public RegisterTenantResponseDTO register(RegisterTenantRequestDTO request) {
        if (repository.existsById(request.getTenantId())) {
            throw new TenantAlreadyExistException("Tenant with the id " + request.getTenantId() + " already exist");
        }

        Tenant tenant = new Tenant();
        tenant.setId(request.getTenantId());
        tenant.setName(request.getTenantName());
        tenant.setEmail(request.getTenantEmail());
        tenant.setHashedPassword(passwordEncoder.encode(request.getPassword()));

        Tenant dbTenant = repository.save(tenant);
        return new RegisterTenantResponseDTO(dbTenant.getId(), dbTenant.getName(), dbTenant.getEmail());
    }

    public RegisterStaffResponseDTO registerStaff(RegisterStaffRequestDTO request) {
        if (staffRepository.findByEmailAndTenantId(request.getEmail(), request.getTenantId()).isPresent()) {
            throw new StaffAlreadyExistException("Staff with the email " + request.getEmail()
                    + " already exist for tenant " + request.getTenantId());
        }

        Staff staff = new Staff();
        staff.setEmail(request.getEmail());
        staff.setHashedPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRole() != null) staff.setRole(request.getRole());
        staff.setTenantId(request.getTenantId());

        Staff dbStaff = staffRepository.save(staff);
        return new RegisterStaffResponseDTO(dbStaff.getTenantId(), dbStaff.getEmail());
    }

}
