package com.application.social_platform.domain.services;

import com.application.social_platform.domain.mapper.UserMapper;
import com.application.social_platform.dto.request.user.CreateUserRequest;
import com.application.social_platform.dto.request.user.UpdateUserRequest;
import com.application.social_platform.dto.response.user.UserResponse;
import com.application.social_platform.entity.User;
import com.application.social_platform.exception.AppException;
import com.application.social_platform.exception.ErrorCode;
import com.application.social_platform.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse create(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        if (userRepository.existsByEmail(createUserRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXIST);

        var localDate = createUserRequest.getDob();

        var user = userMapper.toUser(createUserRequest);
        user.setAge(ChronoUnit.YEARS.between(localDate, LocalDate.now()));
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse findOne(Long id) {

        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        LocalDate localDate = updateUserRequest.getDob();

        userMapper.updateUser(user, updateUserRequest);
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        user.setAge(ChronoUnit.YEARS.between(localDate, LocalDate.now()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void remove(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(id);
    }

}
