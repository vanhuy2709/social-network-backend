package com.application.social_platform.domain.services;

import com.application.social_platform.domain.mapper.RoleMapper;
import com.application.social_platform.dto.request.role.CreateRoleRequest;
import com.application.social_platform.dto.response.role.RoleResponse;
import com.application.social_platform.entity.Role;
import com.application.social_platform.exception.AppException;
import com.application.social_platform.exception.ErrorCode;
import com.application.social_platform.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public RoleResponse create(CreateRoleRequest createRoleRequest) {
        var role = roleMapper.toRole(createRoleRequest);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        roleRepository.deleteById(name);
    }
}
