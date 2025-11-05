package com.example.spring_filter.controller;

import com.example.spring_filter.constant.APIUrl;
import com.example.spring_filter.dto.request.RoleRequest;
import com.example.spring_filter.dto.request.SearchRoleRequest;
import com.example.spring_filter.dto.response.CommonResponse;
import com.example.spring_filter.dto.response.RoleResponse;
import com.example.spring_filter.entity.Role;
import com.example.spring_filter.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.ROLE)
public class RoleController {

    @Autowired
    private final RoleService roleService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<RoleResponse>> createRole(
            @RequestBody RoleRequest roleRequest
            ) {
        RoleResponse roleResponse = roleService.createRole(roleRequest);
        CommonResponse<RoleResponse> commonResponse = CommonResponse.<RoleResponse>builder()
                .data(roleResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping(produces = "application/json", path = APIUrl.PATH_VAR_ROLE_ID)
    public ResponseEntity<CommonResponse<Role>> getRoleById(
            @PathVariable("roleId") String roleId
    ) {
        Role role = roleService.getById(roleId);
        CommonResponse<Role> commonResponse = CommonResponse.<Role>builder()
                .data(role)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<List<RoleResponse>>> getAllRoles(
        @RequestParam(name = "roleName", required = false) String roleName
    ){
        SearchRoleRequest searchRoleRequest = SearchRoleRequest.builder()
                .roleName(roleName)
                .build();

        List<RoleResponse> allRoles  = roleService.getAllRoles(searchRoleRequest);

        CommonResponse<List<RoleResponse>> response = CommonResponse.<List<RoleResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieving all roles")
                .data(allRoles)
                .build();

        return ResponseEntity.ok(response);

    }

    @PutMapping(path = APIUrl.PATH_VAR_ROLE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateRoleById(
            @PathVariable("roleId") String roleId,
            @RequestBody RoleRequest roleRequest)
    {
        String role = roleService.updateRoleById(roleId, roleRequest);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully updated role")
                .data(role)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ROLE_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteRoleById(
            @PathVariable("roleId") String roleId) {
        String role = roleService.deleteRoleById(roleId);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully deleted role")
                .data(role)
                .build();
        return ResponseEntity.ok(response);
    }
}
