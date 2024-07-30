package com.application.social_platform.domain.mapper;

import com.application.social_platform.dto.request.auth.RegisterRequest;
import com.application.social_platform.dto.request.user.CreateUserRequest;
import com.application.social_platform.dto.request.user.UpdateUserRequest;
import com.application.social_platform.dto.response.user.UserResponse;
import com.application.social_platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    User toUser(RegisterRequest registerRequest);
    User toUser(CreateUserRequest createUserRequest);
    void updateUser(@MappingTarget User user, UpdateUserRequest updateUserRequest);
}
