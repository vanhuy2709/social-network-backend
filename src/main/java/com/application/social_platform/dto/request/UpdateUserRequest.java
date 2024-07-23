package com.application.social_platform.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    int age;
    String location;
    LocalDate dob;
    String phoneNumber;
    String avatar;
}
