package com.assignment.dto;

import com.assignment.constants.Role;
import com.assignment.dto.request.UserRequest;
import com.assignment.dto.response.UserResponse;
import com.assignment.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDto {

    private final PasswordEncoder passwordEncoder;

    public UserEntity requestToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .role(Role.USER)
                .password(passwordEncoder.encode(userRequest.getPassword())).build();
    }

    public UserResponse entityToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail()).build();
    }

    public UserEntity updateUser(UserRequest userRequest, UserEntity userEntity) {
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setName(userRequest.getName());
        return userEntity;
    }
}
