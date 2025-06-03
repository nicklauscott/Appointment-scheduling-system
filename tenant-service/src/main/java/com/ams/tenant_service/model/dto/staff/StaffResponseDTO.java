package com.ams.tenant_service.model.dto.staff;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StaffResponseDTO {
   private String email;
   private String firstName;
   private String lastName;
   private String gender;
   private String mobile;
   private String address;
   private LocalDate dateOfBirth;
   private String profilePictureUrl;
   private List<WeeklyScheduleDTO> weeklySchedule;
   private List<CustomScheduleDTO> customSchedules;
}
