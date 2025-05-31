package com.ams.appointment_service.grpc.server.auth;

import auth.AuthServiceGrpc;
import auth.TenantRequestDto;
import auth.TenantResponseDto;
import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.appointment_service.service.AuthService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@AllArgsConstructor
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    private final AuthService authService;

    @Override
    public void createTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        try {
            TenantContext.INSTANCE.setCurrentTenant("public");
            boolean response = authService.createTenant(request.getTenantId());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(response).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while creating tenant %s", e);
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }

    @Override
    public void deleteTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        try {
            TenantContext.INSTANCE.setCurrentTenant("public");
            boolean response = authService.deleteTenant(request.getTenantId());
            responseObserver.onNext(TenantResponseDto.newBuilder().setSuccessful(response).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while deleting tenant %s", e);
        } finally {
            TenantContext.INSTANCE.clear();
        }
    }

}
