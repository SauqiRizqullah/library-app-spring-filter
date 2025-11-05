package com.example.spring_filter.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRoleRequest {
    private String roleName;
}
