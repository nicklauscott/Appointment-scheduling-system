package com.ams.appointment_service.grpc.staff;

import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.grpc.staff.mapper.GrpcStaffMapper;
import com.ams.appointment_service.service.StaffService;
import com.ams.appointment_service.service.StaffSnapshotMapperService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;
import staff.*;
import java.util.UUID;

@Slf4j
@GrpcService
@AllArgsConstructor
public class StaffGrpcService extends StaffServiceGrpc.StaffServiceImplBase {

    private final StaffService service;

    @Override
    public void confirmAppointment(ConfirmAppointmentRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Switch tenant
            service.confirmAppointment(Long.parseLong(request.getAppointmentId()), request.getConfirm());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while confirming appointment %s", e);
        }
    }

    @Override
    public void confirmAppointmentRescheduled(ConfirmAppointmentRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Switch tenant
            service.confirmAppointmentReschedule(Long.parseLong(request.getAppointmentId()), request.getConfirm());
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while confirming rescheduled appointment %s", e);
        }
    }

    @Override
    public void deleteAppointment(DeleteAppointmentRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Switch tenant
            service.deleteAppointment(Long.parseLong(request.getAppointmentId()), UUID.fromString(request.getStaffId()));
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while deleting appointment %s", e);
        }
    }

    @Override
    public void getAllAppointmentsForStaff(GetAppointmentsRequest request, StreamObserver<GetAppointmentsResponse> responseObserver) {
        try {
            // Switch tenant
            var allAppointmentForAStaff = service.getAllAppointmentForAStaff(UUID.fromString(request.getStaffId()));
            GetAppointmentsResponse response = GrpcStaffMapper.toDTo(allAppointmentForAStaff);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while retrieving appointment %s", e);
        }
    }

    @Override
    public void editAppointment(EditAppointmentRequestDto request, StreamObserver<AppointmentResponseDto> responseObserver) {
        try {
            // Switch tenant
            UpdateDTO updateDTO = GrpcStaffMapper.toDTO(request);
            UpdateDTO response = service.editAppointment(updateDTO);
            responseObserver.onNext(GrpcStaffMapper.toDTO(response));
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while editing appointment %s", e);
        }
    }

    @Override
    public void updateStaffSchedule(StaffScheduleSnapshot request, StreamObserver<Empty> responseObserver) {
        try {
            // Switch tenant
            var staffSnapShot = service.getStaffSnapShot(UUID.fromString(request.getId()));
            if (staffSnapShot.isEmpty()) throw new RuntimeException("StaffSnapShot not found");
            var updatedSnapShot = StaffSnapshotMapperService.toEntity(staffSnapShot.get(), request);
            service.updateStaffSchedule(updatedSnapShot);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error occurred while editing appointment %s", e);
        }
    }
}

