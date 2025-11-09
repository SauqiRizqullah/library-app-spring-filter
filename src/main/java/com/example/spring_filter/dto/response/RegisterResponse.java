package com.example.spring_filter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private String adminId;
    private String adminName;
    private String email;
    private String username;
    private List<String> roles;
}
