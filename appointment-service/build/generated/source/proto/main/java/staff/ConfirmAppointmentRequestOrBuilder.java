// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: staff_service.proto

// Protobuf Java Version: 3.25.5
package staff;

public interface ConfirmAppointmentRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ConfirmAppointmentRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string tenant_id = 1;</code>
   * @return The tenantId.
   */
  java.lang.String getTenantId();
  /**
   * <code>string tenant_id = 1;</code>
   * @return The bytes for tenantId.
   */
  com.google.protobuf.ByteString
      getTenantIdBytes();

  /**
   * <code>string appointment_id = 2;</code>
   * @return The appointmentId.
   */
  java.lang.String getAppointmentId();
  /**
   * <code>string appointment_id = 2;</code>
   * @return The bytes for appointmentId.
   */
  com.google.protobuf.ByteString
      getAppointmentIdBytes();

  /**
   * <code>bool confirm = 3;</code>
   * @return The confirm.
   */
  boolean getConfirm();

  /**
   * <code>string staff_id = 4;</code>
   * @return The staffId.
   */
  java.lang.String getStaffId();
  /**
   * <code>string staff_id = 4;</code>
   * @return The bytes for staffId.
   */
  com.google.protobuf.ByteString
      getStaffIdBytes();
}
