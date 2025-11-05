package com.example.spring_filter.service;

import com.example.spring_filter.dto.request.AdminRequest;
import com.example.spring_filter.dto.request.SearchAdminRequest;
import com.example.spring_filter.dto.response.AdminResponse;
import com.example.spring_filter.entity.Admin;
import org.springframework.data.domain.Page;

public interface AdminService {
    AdminResponse createAdmin(AdminRequest adminRequest);

    Admin getById(String adminId);

    Page<Admin> getAllAdmins(SearchAdminRequest searchAdminRequest);

    String deleteAdminById(String adminId);

    String updateAdminById(String adminId, String adminName);
}
