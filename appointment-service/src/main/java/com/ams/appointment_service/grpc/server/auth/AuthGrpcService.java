package com.ams.appointment_service.grpc.server.auth;

import auth.*;
import com.ams.appointment_service.exception.StaffAlreadyExistException;
import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.appointment_service.multitenancy.schema.sevice.TenantSchemaService;
import com.ams.appointment_service.service.StaffService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@AllArgsConstructor
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    private final TenantSchemaService tenantService;
    private final StaffService staffService;

    @Override
    public void createTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        try {
            tenantService.createTenantSchema(request.getTenantId());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while creating tenant schema {}", e.getMessage());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        try {
            tenantService.dropTenantSchema(request.getTenantId());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while deleting tenant schema {}", e.getMessage());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(false).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void createTenantStaff(TenantStaffRequestDto request, StreamObserver<TenantStaffResponseDto> responseObserver) {
        try {
            TenantContext.INSTANCE.setCurrentTenant(request.getTenantId());
            String staffId = staffService.createStaff(request.getStaffEmail());
            responseObserver.onNext(TenantStaffResponseDto.newBuilder().setStaffId(staffId).build());
            responseObserver.onCompleted();
        } catch (StaffAlreadyExistException e) {
            responseObserver.onNext(TenantStaffResponseDto.newBuilder().setErrorMessage(e.getMessage()).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while creating tenant staff {}", e.getMessage());
            responseObserver.onCompleted();
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }
}
