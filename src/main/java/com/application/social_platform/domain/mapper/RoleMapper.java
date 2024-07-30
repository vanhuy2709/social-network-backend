package com.application.social_platform.domain.mapper;

import com.application.social_platform.dto.request.role.CreateRoleRequest;
import com.application.social_platform.dto.response.role.RoleResponse;
import com.application.social_platform.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(CreateRoleRequest createRoleRequest);
    RoleResponse toRoleResponse(Role role);
}
