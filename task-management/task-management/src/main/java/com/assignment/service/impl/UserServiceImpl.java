package com.assignment.service.impl;


import com.assignment.constants.Status;
import com.assignment.dto.UserDto;
import com.assignment.dto.request.UserRequest;
import com.assignment.dto.response.UserResponse;
import com.assignment.entity.TaskEntity;
import com.assignment.entity.UserEntity;
import com.assignment.exceptionHandler.UserResponseException;
import com.assignment.repository.UserRepository;
import com.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDto userDto;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserResponse userResponse = null;
        if (Objects.nonNull(userRequest)) {
            UserEntity user = userDto.requestToEntity(userRequest);
            UserEntity savedUser = userRepository.save(user);
            userResponse = userDto.entityToResponse(savedUser);
        }
        return userResponse;
    }

    @Override
    public UserResponse findUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return userDto.entityToResponse(user);
        } else {
            throw new UserResponseException("User not found for ID: " + id);
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws UserResponseException {
        return userRepository.findById(id)
                .map(user -> {
                    user = userDto.updateUser(userRequest, user);
                    user = userRepository.save(user);
                    return userDto.entityToResponse(user);
                })
                .orElseThrow(() -> new UserResponseException("User Not Found"));
    }

    @Override
    public Status deleteUser(Long id) throws UserResponseException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return Status.SUCCESS;
        }
        return Status.FAILED;
    }
}
