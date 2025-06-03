package com.ams.tenant_service.model.dto.staff;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StaffRequestDTO {

    @NotBlank(message = "First name can't be blank")
    @Size(min = 3, max = 15, message = "First name must be between 3 and 15 characters")
    private String firstName;

    @NotBlank(message = "First name can't be blank")
    @Size(min = 3, max = 15, message = "First name must be between 3 and 15 characters")
    private String lastName;

    private String gender;
    private String mobile;
    private String address;
    private LocalDate dateOfBirth;
    private String profilePictureUrl;

}
