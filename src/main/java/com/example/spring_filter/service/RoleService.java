package com.example.spring_filter.service;

import com.example.spring_filter.dto.request.RoleRequest;
import com.example.spring_filter.dto.request.SearchRoleRequest;
import com.example.spring_filter.dto.response.RoleResponse;
import com.example.spring_filter.entity.Role;

import javax.management.relation.RoleResult;
import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);

    Role getById(String roleId);

    List<RoleResponse> getAllRoles(SearchRoleRequest searchRoleRequest);

    String updateRoleById(String roleId, RoleRequest roleRequest);

    String deleteRoleById(String roleId);

    long count();
}
