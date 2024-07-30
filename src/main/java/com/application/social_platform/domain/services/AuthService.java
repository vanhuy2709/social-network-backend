package com.application.social_platform.domain.services;

import com.application.social_platform.domain.mapper.UserMapper;
import com.application.social_platform.dto.request.auth.LoginRequest;
import com.application.social_platform.dto.request.auth.RegisterRequest;
import com.application.social_platform.dto.response.auth.LoginResponse;
import com.application.social_platform.dto.response.auth.VerifyResponse;
import com.application.social_platform.dto.response.user.UserResponse;
import com.application.social_platform.entity.Role;
import com.application.social_platform.entity.User;
import com.application.social_platform.exception.AppException;
import com.application.social_platform.exception.ErrorCode;
import com.application.social_platform.repository.RoleRepository;
import com.application.social_platform.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.secretKey}")
    protected String SECRET_KEY;

    public LoginResponse login(LoginRequest loginRequest) {
        var isExistUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isMatchPassword = passwordEncoder.matches(loginRequest.getPassword(), isExistUser.getPassword());

        if (!isMatchPassword) throw new AppException(ErrorCode.LOGIN_FAIL);

        String token = generateToken(isExistUser);

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXIST);

        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        Role role = roleRepository.findById("user").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        roles.add(role);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public VerifyResponse verify(String token)
            throws JOSEException, ParseException
    {
        JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY);

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean isVerify = signedJWT.verify(jwsVerifier);

        return VerifyResponse.builder()
                .valid(isVerify && expirationTime.after(new Date()))
                .build();
    }

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("social-platform.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("userId", user.getId())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
