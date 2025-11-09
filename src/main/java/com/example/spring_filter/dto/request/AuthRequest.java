package com.example.spring_filter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "Username must be provided!!!")
    private String username;
    @NotBlank(message = "Password must be provided!!!")
    private String password;
}
