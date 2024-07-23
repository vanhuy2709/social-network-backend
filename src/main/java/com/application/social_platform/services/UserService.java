package com.application.social_platform.services;

import com.application.social_platform.dto.request.CreateUserRequest;
import com.application.social_platform.dto.request.UpdateUserRequest;
import com.application.social_platform.entity.User;
import com.application.social_platform.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
    UserRepository userRepository;

    public User create(CreateUserRequest request) {

        User user = User.builder()
                .age(request.getAge())
                .dob(request.getDob())
                .email(request.getEmail())
                .avatar(request.getAvatar())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .location(request.getLocation())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .build();

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
    }

    public User update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));

        user.setAge(request.getAge());
        user.setDob(request.getDob());
        user.setAvatar(request.getAvatar());
        user.setEmail(request.getEmail());
        user.setLocation(request.getLocation());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));

        userRepository.deleteById(id);
    }

}
