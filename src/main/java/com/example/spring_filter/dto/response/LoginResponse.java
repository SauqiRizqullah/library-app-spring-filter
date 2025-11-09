package com.example.spring_filter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String username;
    private String token;
    private List<String> roles;
}
