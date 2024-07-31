package com.application.social_platform.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotEmpty
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Email(message = "EMAIL_INVALID")
    @NotEmpty
    String email;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @NotEmpty
    String location;

    LocalDate dob;

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    String avatar;

    List<String> roles;
}
