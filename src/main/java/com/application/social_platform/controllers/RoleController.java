package com.application.social_platform.controllers;

import com.application.social_platform.domain.services.RoleService;
import com.application.social_platform.dto.request.role.CreateRoleRequest;
import com.application.social_platform.dto.response.ApiResponse;
import com.application.social_platform.dto.response.role.RoleResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(createRoleRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.findAll())
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<String> deleteOne(@PathVariable String name) {
        roleService.delete(name);

        return ApiResponse.<String>builder()
                .result("Delete role success")
                .build();
    }


}
