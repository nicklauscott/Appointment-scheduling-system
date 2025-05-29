package staff;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class StaffServiceGrpc {

  private StaffServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "StaffService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest,
      com.google.protobuf.Empty> getConfirmAppointmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmAppointment",
      requestType = staff.ConfirmAppointmentRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest,
      com.google.protobuf.Empty> getConfirmAppointmentMethod() {
    io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest, com.google.protobuf.Empty> getConfirmAppointmentMethod;
    if ((getConfirmAppointmentMethod = StaffServiceGrpc.getConfirmAppointmentMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getConfirmAppointmentMethod = StaffServiceGrpc.getConfirmAppointmentMethod) == null) {
          StaffServiceGrpc.getConfirmAppointmentMethod = getConfirmAppointmentMethod =
              io.grpc.MethodDescriptor.<staff.ConfirmAppointmentRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmAppointment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.ConfirmAppointmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("ConfirmAppointment"))
              .build();
        }
      }
    }
    return getConfirmAppointmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest,
      com.google.protobuf.Empty> getConfirmAppointmentRescheduledMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmAppointmentRescheduled",
      requestType = staff.ConfirmAppointmentRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest,
      com.google.protobuf.Empty> getConfirmAppointmentRescheduledMethod() {
    io.grpc.MethodDescriptor<staff.ConfirmAppointmentRequest, com.google.protobuf.Empty> getConfirmAppointmentRescheduledMethod;
    if ((getConfirmAppointmentRescheduledMethod = StaffServiceGrpc.getConfirmAppointmentRescheduledMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getConfirmAppointmentRescheduledMethod = StaffServiceGrpc.getConfirmAppointmentRescheduledMethod) == null) {
          StaffServiceGrpc.getConfirmAppointmentRescheduledMethod = getConfirmAppointmentRescheduledMethod =
              io.grpc.MethodDescriptor.<staff.ConfirmAppointmentRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmAppointmentRescheduled"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.ConfirmAppointmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("ConfirmAppointmentRescheduled"))
              .build();
        }
      }
    }
    return getConfirmAppointmentRescheduledMethod;
  }

  private static volatile io.grpc.MethodDescriptor<staff.DeleteAppointmentRequest,
      com.google.protobuf.Empty> getDeleteAppointmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAppointment",
      requestType = staff.DeleteAppointmentRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.DeleteAppointmentRequest,
      com.google.protobuf.Empty> getDeleteAppointmentMethod() {
    io.grpc.MethodDescriptor<staff.DeleteAppointmentRequest, com.google.protobuf.Empty> getDeleteAppointmentMethod;
    if ((getDeleteAppointmentMethod = StaffServiceGrpc.getDeleteAppointmentMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getDeleteAppointmentMethod = StaffServiceGrpc.getDeleteAppointmentMethod) == null) {
          StaffServiceGrpc.getDeleteAppointmentMethod = getDeleteAppointmentMethod =
              io.grpc.MethodDescriptor.<staff.DeleteAppointmentRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAppointment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.DeleteAppointmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("DeleteAppointment"))
              .build();
        }
      }
    }
    return getDeleteAppointmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<staff.GetAppointmentsRequest,
      staff.GetAppointmentsResponse> getGetAllAppointmentsForStaffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllAppointmentsForStaff",
      requestType = staff.GetAppointmentsRequest.class,
      responseType = staff.GetAppointmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.GetAppointmentsRequest,
      staff.GetAppointmentsResponse> getGetAllAppointmentsForStaffMethod() {
    io.grpc.MethodDescriptor<staff.GetAppointmentsRequest, staff.GetAppointmentsResponse> getGetAllAppointmentsForStaffMethod;
    if ((getGetAllAppointmentsForStaffMethod = StaffServiceGrpc.getGetAllAppointmentsForStaffMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getGetAllAppointmentsForStaffMethod = StaffServiceGrpc.getGetAllAppointmentsForStaffMethod) == null) {
          StaffServiceGrpc.getGetAllAppointmentsForStaffMethod = getGetAllAppointmentsForStaffMethod =
              io.grpc.MethodDescriptor.<staff.GetAppointmentsRequest, staff.GetAppointmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllAppointmentsForStaff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.GetAppointmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.GetAppointmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("GetAllAppointmentsForStaff"))
              .build();
        }
      }
    }
    return getGetAllAppointmentsForStaffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<staff.EditAppointmentRequestDto,
      staff.AppointmentResponseDto> getEditAppointmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditAppointment",
      requestType = staff.EditAppointmentRequestDto.class,
      responseType = staff.AppointmentResponseDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.EditAppointmentRequestDto,
      staff.AppointmentResponseDto> getEditAppointmentMethod() {
    io.grpc.MethodDescriptor<staff.EditAppointmentRequestDto, staff.AppointmentResponseDto> getEditAppointmentMethod;
    if ((getEditAppointmentMethod = StaffServiceGrpc.getEditAppointmentMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getEditAppointmentMethod = StaffServiceGrpc.getEditAppointmentMethod) == null) {
          StaffServiceGrpc.getEditAppointmentMethod = getEditAppointmentMethod =
              io.grpc.MethodDescriptor.<staff.EditAppointmentRequestDto, staff.AppointmentResponseDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditAppointment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.EditAppointmentRequestDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.AppointmentResponseDto.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("EditAppointment"))
              .build();
        }
      }
    }
    return getEditAppointmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<staff.StaffScheduleSnapshot,
      com.google.protobuf.Empty> getUpdateStaffScheduleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateStaffSchedule",
      requestType = staff.StaffScheduleSnapshot.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<staff.StaffScheduleSnapshot,
      com.google.protobuf.Empty> getUpdateStaffScheduleMethod() {
    io.grpc.MethodDescriptor<staff.StaffScheduleSnapshot, com.google.protobuf.Empty> getUpdateStaffScheduleMethod;
    if ((getUpdateStaffScheduleMethod = StaffServiceGrpc.getUpdateStaffScheduleMethod) == null) {
      synchronized (StaffServiceGrpc.class) {
        if ((getUpdateStaffScheduleMethod = StaffServiceGrpc.getUpdateStaffScheduleMethod) == null) {
          StaffServiceGrpc.getUpdateStaffScheduleMethod = getUpdateStaffScheduleMethod =
              io.grpc.MethodDescriptor.<staff.StaffScheduleSnapshot, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateStaffSchedule"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  staff.StaffScheduleSnapshot.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new StaffServiceMethodDescriptorSupplier("UpdateStaffSchedule"))
              .build();
        }
      }
    }
    return getUpdateStaffScheduleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StaffServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffServiceStub>() {
        @java.lang.Override
        public StaffServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffServiceStub(channel, callOptions);
        }
      };
    return StaffServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static StaffServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffServiceBlockingV2Stub>() {
        @java.lang.Override
        public StaffServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return StaffServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StaffServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffServiceBlockingStub>() {
        @java.lang.Override
        public StaffServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffServiceBlockingStub(channel, callOptions);
        }
      };
    return StaffServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StaffServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffServiceFutureStub>() {
        @java.lang.Override
        public StaffServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffServiceFutureStub(channel, callOptions);
        }
      };
    return StaffServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void confirmAppointment(staff.ConfirmAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmAppointmentMethod(), responseObserver);
    }

    /**
     */
    default void confirmAppointmentRescheduled(staff.ConfirmAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmAppointmentRescheduledMethod(), responseObserver);
    }

    /**
     */
    default void deleteAppointment(staff.DeleteAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAppointmentMethod(), responseObserver);
    }

    /**
     */
    default void getAllAppointmentsForStaff(staff.GetAppointmentsRequest request,
        io.grpc.stub.StreamObserver<staff.GetAppointmentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllAppointmentsForStaffMethod(), responseObserver);
    }

    /**
     */
    default void editAppointment(staff.EditAppointmentRequestDto request,
        io.grpc.stub.StreamObserver<staff.AppointmentResponseDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditAppointmentMethod(), responseObserver);
    }

    /**
     */
    default void updateStaffSchedule(staff.StaffScheduleSnapshot request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateStaffScheduleMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StaffService.
   */
  public static abstract class StaffServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StaffServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StaffService.
   */
  public static final class StaffServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StaffServiceStub> {
    private StaffServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StaffServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffServiceStub(channel, callOptions);
    }

    /**
     */
    public void confirmAppointment(staff.ConfirmAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmAppointmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmAppointmentRescheduled(staff.ConfirmAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmAppointmentRescheduledMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteAppointment(staff.DeleteAppointmentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAppointmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllAppointmentsForStaff(staff.GetAppointmentsRequest request,
        io.grpc.stub.StreamObserver<staff.GetAppointmentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllAppointmentsForStaffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void editAppointment(staff.EditAppointmentRequestDto request,
        io.grpc.stub.StreamObserver<staff.AppointmentResponseDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditAppointmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateStaffSchedule(staff.StaffScheduleSnapshot request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateStaffScheduleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StaffService.
   */
  public static final class StaffServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<StaffServiceBlockingV2Stub> {
    private StaffServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StaffServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty confirmAppointment(staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty confirmAppointmentRescheduled(staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmAppointmentRescheduledMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteAppointment(staff.DeleteAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public staff.GetAppointmentsResponse getAllAppointmentsForStaff(staff.GetAppointmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllAppointmentsForStaffMethod(), getCallOptions(), request);
    }

    /**
     */
    public staff.AppointmentResponseDto editAppointment(staff.EditAppointmentRequestDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty updateStaffSchedule(staff.StaffScheduleSnapshot request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateStaffScheduleMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service StaffService.
   */
  public static final class StaffServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StaffServiceBlockingStub> {
    private StaffServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StaffServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty confirmAppointment(staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty confirmAppointmentRescheduled(staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmAppointmentRescheduledMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteAppointment(staff.DeleteAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public staff.GetAppointmentsResponse getAllAppointmentsForStaff(staff.GetAppointmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllAppointmentsForStaffMethod(), getCallOptions(), request);
    }

    /**
     */
    public staff.AppointmentResponseDto editAppointment(staff.EditAppointmentRequestDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty updateStaffSchedule(staff.StaffScheduleSnapshot request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateStaffScheduleMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StaffService.
   */
  public static final class StaffServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StaffServiceFutureStub> {
    private StaffServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StaffServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> confirmAppointment(
        staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmAppointmentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> confirmAppointmentRescheduled(
        staff.ConfirmAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmAppointmentRescheduledMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteAppointment(
        staff.DeleteAppointmentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAppointmentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<staff.GetAppointmentsResponse> getAllAppointmentsForStaff(
        staff.GetAppointmentsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllAppointmentsForStaffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<staff.AppointmentResponseDto> editAppointment(
        staff.EditAppointmentRequestDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditAppointmentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateStaffSchedule(
        staff.StaffScheduleSnapshot request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateStaffScheduleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONFIRM_APPOINTMENT = 0;
  private static final int METHODID_CONFIRM_APPOINTMENT_RESCHEDULED = 1;
  private static final int METHODID_DELETE_APPOINTMENT = 2;
  private static final int METHODID_GET_ALL_APPOINTMENTS_FOR_STAFF = 3;
  private static final int METHODID_EDIT_APPOINTMENT = 4;
  private static final int METHODID_UPDATE_STAFF_SCHEDULE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONFIRM_APPOINTMENT:
          serviceImpl.confirmAppointment((staff.ConfirmAppointmentRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_CONFIRM_APPOINTMENT_RESCHEDULED:
          serviceImpl.confirmAppointmentRescheduled((staff.ConfirmAppointmentRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_DELETE_APPOINTMENT:
          serviceImpl.deleteAppointment((staff.DeleteAppointmentRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_ALL_APPOINTMENTS_FOR_STAFF:
          serviceImpl.getAllAppointmentsForStaff((staff.GetAppointmentsRequest) request,
              (io.grpc.stub.StreamObserver<staff.GetAppointmentsResponse>) responseObserver);
          break;
        case METHODID_EDIT_APPOINTMENT:
          serviceImpl.editAppointment((staff.EditAppointmentRequestDto) request,
              (io.grpc.stub.StreamObserver<staff.AppointmentResponseDto>) responseObserver);
          break;
        case METHODID_UPDATE_STAFF_SCHEDULE:
          serviceImpl.updateStaffSchedule((staff.StaffScheduleSnapshot) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getConfirmAppointmentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.ConfirmAppointmentRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CONFIRM_APPOINTMENT)))
        .addMethod(
          getConfirmAppointmentRescheduledMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.ConfirmAppointmentRequest,
              com.google.protobuf.Empty>(
                service, METHODID_CONFIRM_APPOINTMENT_RESCHEDULED)))
        .addMethod(
          getDeleteAppointmentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.DeleteAppointmentRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_APPOINTMENT)))
        .addMethod(
          getGetAllAppointmentsForStaffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.GetAppointmentsRequest,
              staff.GetAppointmentsResponse>(
                service, METHODID_GET_ALL_APPOINTMENTS_FOR_STAFF)))
        .addMethod(
          getEditAppointmentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.EditAppointmentRequestDto,
              staff.AppointmentResponseDto>(
                service, METHODID_EDIT_APPOINTMENT)))
        .addMethod(
          getUpdateStaffScheduleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              staff.StaffScheduleSnapshot,
              com.google.protobuf.Empty>(
                service, METHODID_UPDATE_STAFF_SCHEDULE)))
        .build();
  }

  private static abstract class StaffServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StaffServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return staff.StaffServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StaffService");
    }
  }

  private static final class StaffServiceFileDescriptorSupplier
      extends StaffServiceBaseDescriptorSupplier {
    StaffServiceFileDescriptorSupplier() {}
  }

  private static final class StaffServiceMethodDescriptorSupplier
      extends StaffServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StaffServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StaffServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StaffServiceFileDescriptorSupplier())
              .addMethod(getConfirmAppointmentMethod())
              .addMethod(getConfirmAppointmentRescheduledMethod())
              .addMethod(getDeleteAppointmentMethod())
              .addMethod(getGetAllAppointmentsForStaffMethod())
              .addMethod(getEditAppointmentMethod())
              .addMethod(getUpdateStaffScheduleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
