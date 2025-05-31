package com.ams.tenant_service.grpc.server;

import auth.AuthServiceGrpc;
import auth.TenantRequestDto;
import auth.TenantResponseDto;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@AllArgsConstructor
public class AuthGrpcService extends AuthServiceGrpc.AuthServiceImplBase {


    @Override
    public void createTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        super.createTenant(request, responseObserver);
    }

    @Override
    public void deleteTenant(TenantRequestDto request, StreamObserver<TenantResponseDto> responseObserver) {
        super.deleteTenant(request, responseObserver);
    }
}
