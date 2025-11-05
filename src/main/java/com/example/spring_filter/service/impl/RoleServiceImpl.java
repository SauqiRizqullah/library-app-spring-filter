package com.example.spring_filter.service.impl;

import com.example.spring_filter.constant.UserRole;
import com.example.spring_filter.dto.request.RoleRequest;
import com.example.spring_filter.dto.request.SearchRoleRequest;
import com.example.spring_filter.dto.response.RoleResponse;
import com.example.spring_filter.entity.Role;
import com.example.spring_filter.repository.RoleRepository;
import com.example.spring_filter.service.RoleService;
import com.example.spring_filter.specification.RoleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {

        UserRole userRole = UserRole.ROLE_ADMIN;

        Role role = Role.builder()
                .userRole(userRole)
                .build();

        roleRepository.saveAndFlush(role);

        return parseToRoleResponse(role);
    }

    private RoleResponse parseToRoleResponse(Role role) {
        String id;
        if (role.getRoleId() != null) {
            id = role.getRoleId();
        } else {
            id = null;
        }

        return RoleResponse.builder()
                .roleId(id)
                .roleName(role.getUserRole().name())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Role getById(String roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Role not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleResponse> getAllRoles(SearchRoleRequest searchRoleRequest) {
        Specification<Role> roleSpecification = RoleSpecification.getSpecification(searchRoleRequest);
        if (searchRoleRequest.getRoleName() == null) {
            List<Role> roles = roleRepository.findAll(roleSpecification);
            return roles.stream().map(this::parseToRoleResponse).toList();
        } else {
            return roleRepository.findAll(roleSpecification).stream().map(this::parseToRoleResponse).toList();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateRoleById(String roleId, RoleRequest roleRequest) {

        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        role.setUserRole(UserRole.valueOf(roleRequest.getRoleName()));

        roleRepository.saveAndFlush(role);

        return "Role has been updated!!!";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteRoleById(String roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        roleRepository.delete(role);
        return roleId + "'s data has been deleted!!!";
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
