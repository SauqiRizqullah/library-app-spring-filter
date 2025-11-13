package com.example.spring_filter.service.impl;

import com.example.spring_filter.dto.request.AuthRequest;
import com.example.spring_filter.dto.request.RegisterRequest;
import com.example.spring_filter.dto.response.LoginResponse;
import com.example.spring_filter.dto.response.RegisterResponse;
import com.example.spring_filter.entity.Admin;
import com.example.spring_filter.entity.Role;
import com.example.spring_filter.repository.AdminRepository;
import com.example.spring_filter.service.AuthService;
import com.example.spring_filter.service.JwtService;
import com.example.spring_filter.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    private final RoleService roleService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        log.info("Creating a New Account of Admin!!!");
        log.info("");
        log.info("Getting an Admin role...");
        Role role = roleService.getById("R001");
        log.info("Encoding a password...");
        String hashPassword = passwordEncoder.encode(registerRequest.getPassword());
        log.info("Building a teacher object...");
        Admin admin = Admin.builder()
                .email(registerRequest.getEmail())
                .adminName(registerRequest.getAdminName())
                .username(registerRequest.getUsername())
                .password(hashPassword)
                .role(List.of(role))
                .isEnable(true)
                .build();
        log.info("Saving admin account to database...");
        adminRepository.saveAndFlush(admin);

        log.info("Getting roles through authorities...");
        List<String> roles = admin.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        log.info("Returning register response!!!");
        return RegisterResponse.builder()
                .adminId(admin.getAdminId())
                .email(admin.getEmail())
                .adminName(admin.getAdminName())
                .username(admin.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        log.info("Preparing to Log In!!!");
        log.info("");
        log.info("Authentication...");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );

        log.info("Authenticating through authentication manager...");
        Authentication authenticate = authenticationManager.authenticate(authentication);

        log.info("Getting account's principal...");
        Admin admin = (Admin) authenticate.getPrincipal();
        log.info("Generating JWT...");
        String token = jwtService.generateToken(admin);
        log.info("Returning login response!!!");
        return LoginResponse.builder()
                .token(token)
                .username(admin.getUsername())
                .roles(admin.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
