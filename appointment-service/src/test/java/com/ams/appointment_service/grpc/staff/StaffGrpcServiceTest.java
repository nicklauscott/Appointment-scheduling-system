package com.ams.appointment_service.grpc.staff;

import com.ams.appointment_service.grpc.server.staff.StaffGrpcService;
import com.ams.appointment_service.service.StaffService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import staff.ConfirmAppointmentRequest;
import staff.DeleteAppointmentRequest;
import staff.StaffServiceGrpc;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaffGrpcServiceTest {

    private Server grpcServer;
    private ManagedChannel channel;
    private StaffServiceGrpc.StaffServiceBlockingStub stub;

    @Mock
    private StaffService staffService;

    @BeforeEach
    void startGrpcServer() throws IOException {
        MockitoAnnotations.openMocks(this);

        grpcServer = ServerBuilder
                .forPort(9010)
                .addService(new StaffGrpcService(staffService))
                .directExecutor()
                .build()
                .start();

        channel = ManagedChannelBuilder
                .forAddress("localhost", 9010)
                .usePlaintext()
                .build();

        stub = StaffServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    void stopGrpcServer() {
        channel.shutdownNow();
        grpcServer.shutdownNow();
    }

    @Test
    void testConfirmAppointment() {
        ConfirmAppointmentRequest request = ConfirmAppointmentRequest.newBuilder()
                .setAppointmentId("123")
                .setConfirm(true)
                .build();
        doNothing().when(staffService).confirmAppointment(123L, true);
        stub.confirmAppointment(request);
        verify(staffService).confirmAppointment(123L, true);
    }

    //@Test
    void testDeleteAppointment() {
        String staffId = "abc@io.com";
        DeleteAppointmentRequest request = DeleteAppointmentRequest.newBuilder()
                .setAppointmentId("123")
                .setStaffId(staffId.toString())
                .build();
        doNothing().when(staffService)
                .deleteAppointment(123L, staffId);
        stub.deleteAppointment(request);
        verify(staffService).deleteAppointment(123L, staffId);
    }
}

