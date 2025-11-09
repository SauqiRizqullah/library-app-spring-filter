package com.example.spring_filter.service;

import com.example.spring_filter.dto.request.AuthRequest;
import com.example.spring_filter.dto.request.RegisterRequest;
import com.example.spring_filter.dto.response.LoginResponse;
import com.example.spring_filter.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(AuthRequest authRequest);
}
