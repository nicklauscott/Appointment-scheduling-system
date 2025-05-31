package com.ams.tenant_service.grpc.server;

import com.ams.tenant_service.grpc.mapper.TenantMapper;
import com.ams.tenant_service.service.TenantService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import tenant.Tenant;
import tenant.TenantIdRequest;
import tenant.TenantServiceGrpc;

@Slf4j
@GrpcService
@AllArgsConstructor
public class TenantGrpcService extends TenantServiceGrpc.TenantServiceImplBase {

    private final TenantService service;

    @Override
    public void getTenantById(TenantIdRequest request, StreamObserver<Tenant> responseObserver) {
        log.info("Receiving request with tenant id: {}", request.getTenantId());
        try {
            Tenant tenant = TenantMapper.toTenant(service.getTenantById(request.getTenantId()));
            log.info("Sending tenant detail: {}", tenant);
            responseObserver.onNext(tenant);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.info("Error sending tenant detail: {}", e.getMessage());
        }
    }

}
