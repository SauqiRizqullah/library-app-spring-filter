package com.example.spring_filter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JwtClaims {
    private String adminId;
    private List<String> roles;
}
