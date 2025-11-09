package com.example.spring_filter.controller;

import com.example.spring_filter.constant.APIUrl;
import com.example.spring_filter.dto.request.AuthRequest;
import com.example.spring_filter.dto.request.RegisterRequest;
import com.example.spring_filter.dto.response.CommonResponse;
import com.example.spring_filter.dto.response.LoginResponse;
import com.example.spring_filter.dto.response.RegisterResponse;
import com.example.spring_filter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = APIUrl.AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> registerUser(@RequestBody RegisterRequest registerRequest){
        RegisterResponse register = authService.register(registerRequest);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully Register Teacher Account!!!")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<?>> login(
            @RequestBody AuthRequest authRequest
    ){
        LoginResponse loginResponse = authService.login(authRequest);

        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully Login the Teacher Account")
                .data(loginResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
