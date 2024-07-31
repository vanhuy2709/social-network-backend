package com.application.social_platform.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    String email;
    String password;
    String firstName;
    String lastName;
    String location;
    LocalDate dob;
    String phoneNumber;
    String avatar;
    List<String> roles;
}
