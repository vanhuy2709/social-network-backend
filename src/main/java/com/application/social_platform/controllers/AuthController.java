package com.application.social_platform.controllers;

import com.application.social_platform.domain.services.AuthService;
import com.application.social_platform.dto.request.auth.LoginRequest;
import com.application.social_platform.dto.request.auth.RegisterRequest;
import com.application.social_platform.dto.request.auth.VerifyRequest;
import com.application.social_platform.dto.response.ApiResponse;
import com.application.social_platform.dto.response.auth.LoginResponse;
import com.application.social_platform.dto.response.auth.VerifyResponse;
import com.application.social_platform.dto.response.user.UserResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        return ApiResponse.<LoginResponse>builder()
                .result(authService.login(loginRequest))
                .build();
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ApiResponse.<UserResponse>builder()
                .result(authService.register(registerRequest))
                .build();
    }

    @PostMapping("/verify")
    public ApiResponse<VerifyResponse> verifyToken(@RequestBody VerifyRequest verifyRequest)
            throws ParseException, JOSEException
    {
        return ApiResponse.<VerifyResponse>builder()
                .result(authService.verify(verifyRequest.getToken()))
                .build();
    }
}
