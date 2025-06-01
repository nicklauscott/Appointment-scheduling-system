package com.ams.tenant_service.grpc.client;

import com.ams.tenant_service.grpc.mapper.AppointmentsMapper;
import com.ams.tenant_service.grpc.mapper.StaffMapper;
import com.ams.tenant_service.model.constant.AppointmentRequestDTO;
import com.ams.tenant_service.model.constant.AppointmentResponseDTO;
import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import staff.*;
import java.util.Collections;
import java.util.List;

/**
 * Making gRPC request to Appointment Service
 */
@Slf4j
@Service
public class StaffGrpcServiceClient {

    private final StaffServiceGrpc.StaffServiceBlockingStub stub;

    public StaffGrpcServiceClient(
            @Value("${tenant.service.address:localhost}") String serverAddress,
            @Value("${tenant.service.grpc.port:8041}") String serverPort
    ) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serverAddress, Integer.parseInt(serverPort))
                .usePlaintext()
                .build();
        this.stub = StaffServiceGrpc.newBlockingStub(channel);
    }

    public void updateStaffSchedule(Staff staff) {
        try {
            StaffScheduleSnapshot requestDto = StaffMapper.toDTO(staff);
            var response = stub.updateStaffSchedule(requestDto);
            log.info("Received {} from updateStaffSchedule gRPC request to Appointment Service", response);
        } catch (Exception e) {
            log.info("Error making updateStaffSchedule gRPC request to Appointment Service: {}", e.getMessage());
            throw e;
        }
    }

    public void confirmAppointment(String appointmentId, boolean confirm) {
        try {
            ConfirmAppointmentRequest request = ConfirmAppointmentRequest.newBuilder()
                    .setTenantId(TenantContext.INSTANCE.getCurrentTenant())
                    .setAppointmentId(appointmentId)
                    .setConfirm(confirm)
                    .build();
            var response = stub.confirmAppointment(request);
            log.info("Received {} from confirmAppointment gRPC request to Appointment Service", response);
        } catch (Exception e) {
            log.info("Error making confirmAppointment gRPC request to Appointment Service: {}", e.getMessage());
        }
    }

    public void confirmAppointmentRescheduled(String appointmentId, boolean confirm) {
        try {
            ConfirmAppointmentRequest request = ConfirmAppointmentRequest.newBuilder()
                    .setTenantId(TenantContext.INSTANCE.getCurrentTenant())
                    .setAppointmentId(appointmentId)
                    .setConfirm(confirm)
                    .build();
            var response = stub.confirmAppointmentRescheduled(request);
            log.info("Received {} from confirmAppointmentRescheduled gRPC request to Appointment Service", response);
        } catch (Exception e) {
            log.info("Error making confirmAppointmentRescheduled gRPC request to Appointment Service: {}", e.getMessage());
        }
    }

    public void deleteAppointment(String staffId, String appointmentId) {
        try {
            DeleteAppointmentRequest request = DeleteAppointmentRequest.newBuilder()
                    .setTenantId(TenantContext.INSTANCE.getCurrentTenant())
                    .setStaffId(staffId)
                    .setAppointmentId(appointmentId)
                    .build();
            var response = stub.deleteAppointment(request);
            log.info("Received {} from deleteAppointment gRPC request to Appointment Service", response);
        } catch (Exception e) {
            log.info("Error making deleteAppointment gRPC request to Appointment Service: {}", e.getMessage());
        }
    }

    public List<AppointmentResponseDTO> getAllAppointmentsForStaff(String staffId) {
        try {
            GetAppointmentsRequest request = GetAppointmentsRequest.newBuilder()
                    .setTenantId(TenantContext.INSTANCE.getCurrentTenant())
                    .setStaffId(staffId)
                    .build();
            List<AppointmentResponseDTO> response = AppointmentsMapper.toDTOs(stub.getAllAppointmentsForStaff(request));
            log.info("Received {} AppointmentDTO from getAllAppointmentsForStaff gRPC request to Appointment Service",
                    response.size());
            return response;
        } catch (Exception e) {
            log.info("Error making getAllAppointmentsForStaff gRPC request to Appointment Service: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public void editAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        try {
            EditAppointmentRequestDto request = AppointmentsMapper.toDTO(appointmentRequestDTO);
            var response = stub.editAppointment(request);
            log.info("Received {} from editAppointment gRPC request to Appointment Service", response);
        } catch (Exception e) {
            log.info("Error making editAppointment gRPC request to Appointment Service: {}", e.getMessage());
        }
    }

}
