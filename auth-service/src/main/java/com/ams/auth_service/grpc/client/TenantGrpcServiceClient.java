package com.ams.auth_service.grpc.client;

import auth.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TenantGrpcServiceClient {

    private final AuthServiceGrpc.AuthServiceBlockingStub stub;

    public TenantGrpcServiceClient(
            @Value("${tenant.service.address:localhost}") String serverAddress,
            @Value("${tenant.service.grpc.port:8043}") String serverPort
    ) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serverAddress, Integer.parseInt(serverPort))
                .usePlaintext()
                .build();

        this.stub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public TenantResponseDto createTenantSchema(String tenantId) {
        try {
            TenantRequestDto requestDto = TenantRequestDto.newBuilder()
                    .setTenantId(tenantId)
                    .build();
            return stub.createTenant(requestDto);
        } catch (Exception e) {
            log.error("Error creating tenant schema");
        }
        return TenantResponseDto.newBuilder().setSuccessful(false).build();
    }

    public void deleteTenantSchema(String tenantId) {
        try {
            TenantRequestDto requestDto = TenantRequestDto.newBuilder()
                    .setTenantId(tenantId)
                    .build();
            stub.deleteTenant(requestDto);
            return;
        } catch (Exception e) {
            log.error("Error deleting tenant schema");
        }
        TenantResponseDto.newBuilder().setSuccessful(false).build();
    }

    public TenantStaffResponseDto createTenantStaff(String tenantId, String staffEmail) {
        try {
            TenantStaffRequestDto requestDto = TenantStaffRequestDto.newBuilder()
                    .setTenantId(tenantId)
                    .setStaffEmail(staffEmail)
                    .build();
            return stub.createTenantStaff(requestDto);
        } catch (Exception e) {
            log.error("Error while creating a tenant staff");
        }
        return TenantStaffResponseDto.newBuilder().setStaffId("").build();
    }

}
