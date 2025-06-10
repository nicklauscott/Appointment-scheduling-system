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
            System.out.println("tenantResponse: " + tenantResponse.getSuccessful());
            if (!tenantResponse.getSuccessful())
                throw new CreatingGrpcTenantException("Couldn't create tenant schema");
            var appointmentResponse = appointmentGrpcClient.createTenantSchema(tenantId);
            System.out.println("appointmentResponse: " + appointmentResponse.getSuccessful());
            if (!appointmentResponse.getSuccessful())
                throw new CreatingGrpcTenantException("Couldn't create appointment schema");
        } catch (Exception e) {
            tenantGrpcClient.deleteTenantSchema(tenantId);
            appointmentGrpcClient.deleteTenantSchema(tenantId);
            throw new CreatingGrpcTenantException(e.getMessage());
        }
    }

    public void createTenantStaff(String tenantId, String staffEmail) {
        try {
            var tenantResponse = tenantGrpcClient.createTenantStaff(tenantId, staffEmail);
            if (!tenantResponse.getStaffId().equals(staffEmail))
                throw new CreatingGrpcStaffException("Couldn't create staff in tenant service");
            var appointmentResponse = appointmentGrpcClient.createTenantStaff(tenantId, staffEmail);
            if (!appointmentResponse.getStaffId().equals(staffEmail))
                throw new CreatingGrpcStaffException("Couldn't create staff in appointment service");
        } catch (Exception e) {
            tenantGrpcClient.createTenantStaff(tenantId, staffEmail);
            appointmentGrpcClient.createTenantStaff(tenantId, staffEmail);
            throw new CreatingGrpcStaffException(e.getMessage());
        }
    }

}
