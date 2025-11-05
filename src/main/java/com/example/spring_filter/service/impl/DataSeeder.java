package com.example.spring_filter.service.impl;

import com.example.spring_filter.constant.UserRole;
import com.example.spring_filter.dto.request.RoleRequest;
import com.example.spring_filter.dto.response.RoleResponse;
import com.example.spring_filter.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder {

    private final RoleService roleService;

    @PostConstruct
    public void init() {
        if (roleService.count() == 0) {
            log.info("Seeding initial role data...");
            RoleRequest role = new RoleRequest();
            role.setRoleName(UserRole.ROLE_ADMIN.toString());
            RoleResponse roleResponse = roleService.createRole(role);
            log.info("Initial role data seeded.");
        } else {
            log.info("Role data already exists. Skipping seeding.");
        }
    }
}
