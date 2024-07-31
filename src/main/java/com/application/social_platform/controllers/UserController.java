package com.application.social_platform.controllers;

import com.application.social_platform.dto.request.user.CreateUserRequest;
import com.application.social_platform.dto.request.user.UpdateUserRequest;
import com.application.social_platform.dto.response.ApiResponse;
import com.application.social_platform.dto.response.PagingResponse;
import com.application.social_platform.dto.response.user.UserResponse;
import com.application.social_platform.domain.services.UserService;
import com.application.social_platform.entity.User;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody @Valid CreateUserRequest request) {

        return ApiResponse.<UserResponse>builder()
                .code(201)
                .result(userService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAll() {

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAll())
                .build();
    }

    @GetMapping("/pagination")
    ApiResponse<PagingResponse<List<UserResponse>>> getAllWithPagination(
            @RequestParam int page, @RequestParam int pageSize
    ) {
        int pageNumber = page - 1;
        return ApiResponse.<PagingResponse<List<UserResponse>>>builder()
                .result(userService.findAllWithPagination(pageNumber, pageSize))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getOne(@PathVariable Long userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findOne(userId))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> update(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.update(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> remove(@PathVariable Long userId) {
        userService.remove(userId);

        return ApiResponse.<String>builder()
                .result("Delete user successfully")
                .build();
    }
}
