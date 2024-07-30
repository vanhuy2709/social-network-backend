package com.application.social_platform.dto.response.user;

import com.application.social_platform.dto.response.role.RoleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String username;
    String email;
    String firstName;
    String lastName;
    long age;
    String location;
    LocalDate dob;
    String phoneNumber;
    String avatar;
    Set<RoleResponse> roles;
}
