package com.ams.tenant_service.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
public class StaffResponseDTO {
   private String id;
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
