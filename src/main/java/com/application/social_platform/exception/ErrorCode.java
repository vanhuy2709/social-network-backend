package com.application.social_platform.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    // User
    USER_NOT_FOUND(404, "User is not found", HttpStatus.NOT_FOUND),
    USER_EXISTED(409, "Username is existed", HttpStatus.CONFLICT),
    USERNAME_INVALID(400, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(400, "Email invalid", HttpStatus.BAD_REQUEST),
    EMAIL_EXIST(409, "Email is existed", HttpStatus.CONFLICT),
    PASSWORD_INVALID(400, "Password must be at least 6 characters", HttpStatus.BAD_REQUEST),

    // Auth
    LOGIN_FAIL(400, "Username / Password is invalid", HttpStatus.BAD_REQUEST),

    // Role
    ROLE_NOT_FOUND(404, "Role is not found", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
