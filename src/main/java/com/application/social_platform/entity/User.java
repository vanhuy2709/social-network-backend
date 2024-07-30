package com.application.social_platform.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "`User`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    long age;
    String location;
    LocalDate dob;
    String phoneNumber;
    String avatar;

    @ManyToMany
    Set<Role> roles;
}
