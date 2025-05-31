package com.ams.notification_service.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tenant.Tenant;
import tenant.TenantIdRequest;
import tenant.TenantServiceGrpc;

/**
 * Making gRPC request to Tenant Service
 */
@Service
public class TenantServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(TenantServiceGrpcClient.class);
    private final TenantServiceGrpc.TenantServiceBlockingStub blockingStub;

    public TenantServiceGrpcClient(
            @Value("${tenant.service.address:localhost}") String serverAddress,
            @Value("${tenant.service.grpc.port:8043}") String serverPort
    ) {
        log.info("Connecting to GRPC Tenant service at {}:{}", serverAddress, serverPort);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serverAddress, Integer.parseInt(serverPort))
                .usePlaintext()
                .build();
        this.blockingStub = TenantServiceGrpc.newBlockingStub(channel);
    }

    public Tenant getTenantDetails(String tenantId) {
        try {
            log.info("Making GRPC request to Tenant service: {}", tenantId);
            TenantIdRequest request = TenantIdRequest.newBuilder()
                    .setTenantId(tenantId)
                    .build();

            Tenant response = blockingStub.getTenantById(request);
            log.info("Received GRPC tenant service: {}", response.toString());
            return response;
        } catch (Exception e) {
            log.info("Received GRPC tenant service: ", e);
        }
        return null;
    }

}
