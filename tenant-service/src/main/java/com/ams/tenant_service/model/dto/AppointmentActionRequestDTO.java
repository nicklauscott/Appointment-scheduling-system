package com.ams.tenant_service.model.dto;

import com.ams.tenant_service.model.constant.OnConfirm;
import com.ams.tenant_service.model.constant.OnDelete;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class AppointmentActionRequestDTO {
    String appointmentId;
    @NotNull(groups = {OnConfirm.class})
    @Null(groups = {OnDelete.class})
    boolean confirm;
}
