package com.example.spring_filter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminResponse {
    private String adminId;
    private String roleId;
    private String adminName;
    private String username;
    private String email;
}
