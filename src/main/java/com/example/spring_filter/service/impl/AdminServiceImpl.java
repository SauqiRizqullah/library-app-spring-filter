package com.example.spring_filter.service.impl;

import com.example.spring_filter.dto.request.AdminRequest;
import com.example.spring_filter.dto.request.SearchAdminRequest;
import com.example.spring_filter.dto.response.AdminResponse;
import com.example.spring_filter.entity.Admin;
import com.example.spring_filter.entity.Role;
import com.example.spring_filter.repository.AdminRepository;
import com.example.spring_filter.service.AdminService;
import com.example.spring_filter.service.RoleService;
import com.example.spring_filter.specification.AdminSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final RoleService roleService;

    @Override
    public AdminResponse createAdmin(AdminRequest adminRequest) {

        Role role = roleService.getById("R001");

        Admin admin = Admin.builder()
//                .role(role)
                .adminName(adminRequest.getAdminName())
                .username(adminRequest.getUsername())
                .password(adminRequest.getPassword())
                .email(adminRequest.getEmail())
                .build();

        adminRepository.saveAndFlush(admin);

        return parseToAdminResponse(admin);
    }

    private AdminResponse parseToAdminResponse(Admin admin) {
        String id;
        if (admin.getAdminId() != null) {
            id = admin.getAdminId();
        } else {
            id = null;
        }
        return AdminResponse.builder()
                .adminId(id)
//                .roleId(admin.getRole().getRoleId())
                .adminName(admin.getAdminName())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .build();
    }

    @Override
    public Admin getById(String adminId) {
        return adminRepository.findById(adminId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
    }

    @Override
    public Page<Admin> getAllAdmins(SearchAdminRequest searchAdminRequest) {

        if(searchAdminRequest.getPage() <= 0){
            searchAdminRequest.setPage(1);
        }

        String validSortBy;
        if("adminName".equalsIgnoreCase(searchAdminRequest.getSortBy())){
            validSortBy = searchAdminRequest.getSortBy();
        } else {
            validSortBy = "adminId";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(searchAdminRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of(searchAdminRequest.getPage() - 1, searchAdminRequest.getSize(), sort);

        Specification<Admin> specification = AdminSpecification.getSpecification(searchAdminRequest);
        return adminRepository.findAll(specification, pageable);
    }

    @Override
    public String deleteAdminById(String adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
        adminRepository.delete(admin);

        return "Admin has been deleted!!!";
    }

    @Override
    public String updateAdminById(String adminId, String adminName) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));

       admin.setAdminName(adminName);
//       admin.setUsername(admin.getUsername());
//       admin.setPassword(admin.getPassword());
//       admin.setEmail(admin.getEmail());
        adminRepository.saveAndFlush(admin);

        return "Admin has been updated!!!";
    }
}
