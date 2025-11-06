package com.example.spring_filter.controller;

import com.example.spring_filter.constant.APIUrl;
import com.example.spring_filter.dto.request.AdminRequest;
import com.example.spring_filter.dto.request.SearchAdminRequest;
import com.example.spring_filter.dto.response.AdminResponse;
import com.example.spring_filter.dto.response.CommonResponse;
import com.example.spring_filter.dto.response.PagingResponse;
import com.example.spring_filter.entity.Admin;
import com.example.spring_filter.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.ADMIN)
public class AdminController {

//    @GetMapping(produces = "application/json")
//    public String hello() {
//        return "{\"message\": \"Hello, World!\"}";
//    }
    @Autowired
    private final AdminService adminService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<AdminResponse>> createAdmin(
            @RequestBody AdminRequest adminRequest
            ){
        AdminResponse admin = adminService.createAdmin(adminRequest);

        CommonResponse<AdminResponse> commonResponse = CommonResponse.<AdminResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Admin created successfully")
                .data(admin)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping(produces = "application/json", path = APIUrl.PATH_VAR_ADMIN_ID)
    public ResponseEntity<CommonResponse<Admin>> getById(
            @PathVariable("adminId") String adminId
    ){
        Admin admin = adminService.getById(adminId);

        CommonResponse<Admin> commonResponse = CommonResponse.<Admin>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Admin retrieved successfully")
                .data(admin)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Admin>>> getAllAdmins(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "menuId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "adminName", required = false) String adminName
    ){
        SearchAdminRequest searchAdminRequest = SearchAdminRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .adminName(adminName)
                .build();

        Page<Admin> allAdmins = adminService.getAllAdmins(searchAdminRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(allAdmins.getPageable().getPageNumber() + 1)
                .size(allAdmins.getPageable().getPageSize())
                .totalPages(allAdmins.getTotalPages())
                .totalElements(allAdmins.getTotalElements())
                .hasNext(allAdmins.hasNext())
                .hasPrevious(allAdmins.hasPrevious())
                .build();

        CommonResponse<Page<Admin>> commonResponse = CommonResponse.<Page<Admin>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieving all admins")
                .data(allAdmins)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping(produces = "application/json", path = APIUrl.PATH_VAR_ADMIN_ID)
    public ResponseEntity<String> deleteAdminById(
            @PathVariable("adminId") String adminId
    ){
        String adminById = adminService.deleteAdminById(adminId);

        return ResponseEntity.ok(adminById);
    }

    @PutMapping(produces = "application/json", path = APIUrl.PATH_VAR_ADMIN_ID)
    public ResponseEntity<String> updateAdminById(
            @PathVariable("adminId") String adminId,
            @RequestParam("adminName") String adminName){
        String admin = adminService.updateAdminById(adminId, adminName);
        return ResponseEntity.ok(admin);
    }

}
