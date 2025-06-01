package auth;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.1)",
    comments = "Source: auth_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<auth.TenantRequestDto,
      auth.TenantResponseDto> getCreateTenantMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTenant",
      requestType = auth.TenantRequestDto.class,
      responseType = auth.TenantResponseDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<auth.TenantRequestDto,
      auth.TenantResponseDto> getCreateTenantMethod() {
    io.grpc.MethodDescriptor<auth.TenantRequestDto, auth.TenantResponseDto> getCreateTenantMethod;
    if ((getCreateTenantMethod = AuthServiceGrpc.getCreateTenantMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getCreateTenantMethod = AuthServiceGrpc.getCreateTenantMethod) == null) {
          AuthServiceGrpc.getCreateTenantMethod = getCreateTenantMethod =
              io.grpc.MethodDescriptor.<auth.TenantRequestDto, auth.TenantResponseDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTenant"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantRequestDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantResponseDto.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("CreateTenant"))
              .build();
        }
      }
    }
    return getCreateTenantMethod;
  }

  private static volatile io.grpc.MethodDescriptor<auth.TenantRequestDto,
      auth.TenantResponseDto> getDeleteTenantMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTenant",
      requestType = auth.TenantRequestDto.class,
      responseType = auth.TenantResponseDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<auth.TenantRequestDto,
      auth.TenantResponseDto> getDeleteTenantMethod() {
    io.grpc.MethodDescriptor<auth.TenantRequestDto, auth.TenantResponseDto> getDeleteTenantMethod;
    if ((getDeleteTenantMethod = AuthServiceGrpc.getDeleteTenantMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getDeleteTenantMethod = AuthServiceGrpc.getDeleteTenantMethod) == null) {
          AuthServiceGrpc.getDeleteTenantMethod = getDeleteTenantMethod =
              io.grpc.MethodDescriptor.<auth.TenantRequestDto, auth.TenantResponseDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTenant"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantRequestDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantResponseDto.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("DeleteTenant"))
              .build();
        }
      }
    }
    return getDeleteTenantMethod;
  }

  private static volatile io.grpc.MethodDescriptor<auth.TenantStaffRequestDto,
      auth.TenantStaffResponseDto> getCreateTenantStaffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTenantStaff",
      requestType = auth.TenantStaffRequestDto.class,
      responseType = auth.TenantStaffResponseDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<auth.TenantStaffRequestDto,
      auth.TenantStaffResponseDto> getCreateTenantStaffMethod() {
    io.grpc.MethodDescriptor<auth.TenantStaffRequestDto, auth.TenantStaffResponseDto> getCreateTenantStaffMethod;
    if ((getCreateTenantStaffMethod = AuthServiceGrpc.getCreateTenantStaffMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getCreateTenantStaffMethod = AuthServiceGrpc.getCreateTenantStaffMethod) == null) {
          AuthServiceGrpc.getCreateTenantStaffMethod = getCreateTenantStaffMethod =
              io.grpc.MethodDescriptor.<auth.TenantStaffRequestDto, auth.TenantStaffResponseDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTenantStaff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantStaffRequestDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  auth.TenantStaffResponseDto.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("CreateTenantStaff"))
              .build();
        }
      }
    }
    return getCreateTenantStaffMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @java.lang.Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @java.lang.Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @java.lang.Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createTenant(auth.TenantRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantResponseDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTenantMethod(), responseObserver);
    }

    /**
     */
    default void deleteTenant(auth.TenantRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantResponseDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteTenantMethod(), responseObserver);
    }

    /**
     */
    default void createTenantStaff(auth.TenantStaffRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantStaffResponseDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTenantStaffMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthService.
   */
  public static abstract class AuthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void createTenant(auth.TenantRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantResponseDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTenantMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTenant(auth.TenantRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantResponseDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteTenantMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTenantStaff(auth.TenantStaffRequestDto request,
        io.grpc.stub.StreamObserver<auth.TenantStaffResponseDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTenantStaffMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public auth.TenantResponseDto createTenant(auth.TenantRequestDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTenantMethod(), getCallOptions(), request);
    }

    /**
     */
    public auth.TenantResponseDto deleteTenant(auth.TenantRequestDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTenantMethod(), getCallOptions(), request);
    }

    /**
     */
    public auth.TenantStaffResponseDto createTenantStaff(auth.TenantStaffRequestDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTenantStaffMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthService.
   */
  public static final class AuthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<auth.TenantResponseDto> createTenant(
        auth.TenantRequestDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTenantMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<auth.TenantResponseDto> deleteTenant(
        auth.TenantRequestDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteTenantMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<auth.TenantStaffResponseDto> createTenantStaff(
        auth.TenantStaffRequestDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTenantStaffMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TENANT = 0;
  private static final int METHODID_DELETE_TENANT = 1;
  private static final int METHODID_CREATE_TENANT_STAFF = 2;

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
        case METHODID_CREATE_TENANT:
          serviceImpl.createTenant((auth.TenantRequestDto) request,
              (io.grpc.stub.StreamObserver<auth.TenantResponseDto>) responseObserver);
          break;
        case METHODID_DELETE_TENANT:
          serviceImpl.deleteTenant((auth.TenantRequestDto) request,
              (io.grpc.stub.StreamObserver<auth.TenantResponseDto>) responseObserver);
          break;
        case METHODID_CREATE_TENANT_STAFF:
          serviceImpl.createTenantStaff((auth.TenantStaffRequestDto) request,
              (io.grpc.stub.StreamObserver<auth.TenantStaffResponseDto>) responseObserver);
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
          getCreateTenantMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              auth.TenantRequestDto,
              auth.TenantResponseDto>(
                service, METHODID_CREATE_TENANT)))
        .addMethod(
          getDeleteTenantMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              auth.TenantRequestDto,
              auth.TenantResponseDto>(
                service, METHODID_DELETE_TENANT)))
        .addMethod(
          getCreateTenantStaffMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              auth.TenantStaffRequestDto,
              auth.TenantStaffResponseDto>(
                service, METHODID_CREATE_TENANT_STAFF)))
        .build();
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return auth.AuthServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getCreateTenantMethod())
              .addMethod(getDeleteTenantMethod())
              .addMethod(getCreateTenantStaffMethod())
              .build();
        }
      }
    }
    return result;
  }
}
