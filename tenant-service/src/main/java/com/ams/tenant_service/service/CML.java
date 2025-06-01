package com.ams.tenant_service.service;

import com.ams.tenant_service.model.entities.Tenant;
import com.ams.tenant_service.model.entities.tenant.CustomSchedule;
import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.tenant_service.repository.CustomScheduleRepository;
import com.ams.tenant_service.repository.StaffRepository;
import com.ams.tenant_service.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
//@Component
@AllArgsConstructor
public class CML implements CommandLineRunner {

    private final StaffRepository repository;
    private final CustomScheduleRepository customSchedule;
    private final TenantRepository tenantRepository;
    private final TenantService service;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        TenantContext.INSTANCE.setCurrentTenant("public");
        Tenant tenant = new Tenant();
        tenant.setTenantId("abc");
        tenantRepository.save(tenant);

        var ten = service.getTenantById("abc");
        log.info("------------- Tenants: {}", ten);

        TenantContext.INSTANCE.setCurrentTenant("abc");
        Staff staff = getStaff();
        for (int i = 0; i < 2; i++) {
            log.info("Saved staff: {}", getStaffWithCustomSchedule(staff, i));
        }
    }


    public Staff getStaff() {
        Staff staff = new Staff();
        staff.setEmail("abc@io.io");
        staff.setFirstName("Abc");
        staff.setLastName("Def");
        repository.save(staff);
        List<Staff> staffList = repository.findAll();
        if (staffList.isEmpty()) {
            System.out.println("Staff list is empty");
            return null;
        }
        return staffList.getFirst();
    }

    public Staff getStaffWithCustomSchedule(Staff staff, int count) {
        CustomSchedule schedule = new CustomSchedule();
        schedule.setDate(LocalDate.now().plusDays(count));
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));
        schedule.setAvailable(true);
        schedule.setStaff(staff);
        customSchedule.save(schedule);
        return staff;
    }
}
