package com.application.social_platform.controllers;

import com.application.social_platform.dto.request.CreateUserRequest;
import com.application.social_platform.dto.request.UpdateUserRequest;
import com.application.social_platform.dto.response.ApiResponse;
import com.application.social_platform.entity.User;
import com.application.social_platform.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> create(@RequestBody CreateUserRequest request) {

        return ApiResponse.<User>builder()
                .statusCode(201)
                .result(userService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<User>> getAll() {
        return ApiResponse.<List<User>>builder()
                .statusCode(200)
                .result(userService.findAll())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<User> getOne(@PathVariable Long userId) {
        return ApiResponse.<User>builder()
                .statusCode(200)
                .result(userService.findOne(userId))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<User> update(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return ApiResponse.<User>builder()
                .statusCode(200)
                .result(userService.update(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> remove(@PathVariable Long userId) {
        userService.remove(userId);

        return ApiResponse.<String>builder()
                .statusCode(200)
                .result("Delete user successfully")
                .build();
    }
}
