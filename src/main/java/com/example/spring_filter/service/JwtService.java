package com.example.spring_filter.service;

import com.example.spring_filter.dto.response.JwtClaims;
import com.example.spring_filter.entity.Admin;

public interface JwtService {
    String generateToken(Admin admin);
    boolean verifyToken(String token);
    JwtClaims getClaimsByToken(String token);
}
