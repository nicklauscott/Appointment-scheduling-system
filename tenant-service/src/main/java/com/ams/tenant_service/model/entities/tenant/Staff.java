package com.ams.tenant_service.model.entities.tenant;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) private String email;
    @Column(name = "first_name", nullable = false) private String firstName;
    @Column(name = "last_name", nullable = false) private String lastName;
    @Column private String gender;
    @Column private String mobile;
    @Column private String address;
    @Column(name = "date_of_birth") private LocalDate dateOfBirth;
    @Column(name = "profile_picture_url") private String profilePictureUrl;

    @Column(name = "job_title") private String jobTitle;
    @Column private String department;
    @Column(name = "employment_start_date") private LocalDate employmentStartDate;
    @Column(name = "employment_end_date") private LocalDate employmentEndDate;
    @Column(name = "employment_type") private String employmentType;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeeklySchedule> weeklySchedule;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomSchedule> customSchedules;
}


