package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.AppointmentOperationException;
import com.ams.tenant_service.grpc.client.StaffGrpcServiceClient;
import com.ams.tenant_service.model.constant.AppointmentRequestDTO;
import com.ams.tenant_service.model.constant.AppointmentResponseDTO;
import com.ams.tenant_service.model.dto.AppointmentActionRequestDTO;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AppointmentService {

    private final StaffGrpcServiceClient grpcClient;

    public String confirmAppointment(AppointmentActionRequestDTO request) {
        try {
            grpcClient.confirmAppointment(request.getAppointmentId(), request.isConfirm());
            return request.getAppointmentId();
        } catch (Exception e) {
            log.info("Error occurred while confirming an appointment. {}", e.getMessage());
            throw new AppointmentOperationException("Couldn't not confirm appointment");
        }
    }

    public String confirmAppointmentRescheduled(AppointmentActionRequestDTO request) {
        try {
            grpcClient.confirmAppointmentRescheduled(request.getAppointmentId(), request.isConfirm());
            return request.getAppointmentId();
        } catch (Exception e) {
            log.info("Error occurred while confirming rescheduled appointment. {}", e.getMessage());
            throw new AppointmentOperationException("Couldn't not confirm appointment reschedule");
        }
    }

    public String deleteAppointment(AppointmentActionRequestDTO request) {
        try {
            String staffId = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
            grpcClient.deleteAppointment(staffId, request.getAppointmentId());
            return request.getAppointmentId();
        } catch (Exception e) {
            log.info("Error occurred while deleteAppointment an appointment. {}", e.getMessage());
            throw new AppointmentOperationException("Couldn't not delete appointment");
        }
    }

    public List<AppointmentResponseDTO> getAllAppointmentsForStaff() {
        try {
            String staffId = TenantContext.INSTANCE.getRequestDetail().get("X-USER-ID");
            return grpcClient.getAllAppointmentsForStaff(staffId);
        } catch (Exception e) {
            log.info("Error occurred while retrieving staff appointments. {}", e.getMessage());
            throw new AppointmentOperationException("Couldn't not retrieve staff appointments");
        }
    }

    public AppointmentRequestDTO editAppointment(AppointmentRequestDTO appointment) {
        try {
            grpcClient.editAppointment(appointment);
            return appointment;
        } catch (Exception e) {
            log.info("Error occurred while editing an appointments. {}", e.getMessage());
            throw new AppointmentOperationException("Couldn't not edit appointments");
        }
    }

}
