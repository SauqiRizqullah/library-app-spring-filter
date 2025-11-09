package com.example.spring_filter.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Name can't be null!!!")
    private String adminName;

    @NotBlank(message = "Username must be provided!!!")
    private String username;

    @NotBlank(message = "Password must be provided!!!")
    private String password;

    @NotBlank(message = "Email must be provided!!!")
    @Email(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Email format is invalid"
    )
    private String email;
}
