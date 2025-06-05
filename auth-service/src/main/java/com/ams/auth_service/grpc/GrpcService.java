package com.ams.auth_service.grpc;

import com.ams.auth_service.exception.CreatingGrpcStaffException;
import com.ams.auth_service.exception.CreatingGrpcTenantException;
import com.ams.auth_service.grpc.client.AppointmentGrpcServiceClient;
import com.ams.auth_service.grpc.client.TenantGrpcServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GrpcService {

    private final AppointmentGrpcServiceClient appointmentGrpcClient;
    private final TenantGrpcServiceClient tenantGrpcClient;

    public void createTenant(String tenantId) {
        try {
            var tenantResponse = tenantGrpcClient.createTenantSchema(tenantId);
            if (!tenantResponse.getSuccessful()) return;
            var appointmentResponse = appointmentGrpcClient.createTenantSchema(tenantId);
            if (appointmentResponse.getSuccessful()) return;
            tenantGrpcClient.deleteTenantSchema(tenantId);
        } catch (Exception e) {
            throw new CreatingGrpcTenantException(e.getMessage());
        }
    }

    public void createTenantStaff(String tenantId, String staffEmail) {
        try {
            var tenantResponse = tenantGrpcClient.createTenantStaff(tenantId, staffEmail);
            if (!tenantResponse.getStaffId().equals(staffEmail)) return;
            var appointmentResponse = appointmentGrpcClient.createTenantStaff(tenantId, staffEmail);
            if (!appointmentResponse.getStaffId().equals(staffEmail)) return;
            tenantGrpcClient.deleteTenantSchema(tenantId);
        } catch (Exception e) {
            throw new CreatingGrpcStaffException(e.getMessage());
        }
    }

}
