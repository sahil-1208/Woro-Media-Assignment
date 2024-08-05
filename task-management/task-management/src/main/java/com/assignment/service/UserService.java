package com.assignment.service;

import com.assignment.constants.Status;
import com.assignment.dto.request.UserRequest;
import com.assignment.dto.response.UserResponse;
import com.assignment.exceptionHandler.UserResponseException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse findUserById(Long id);

    UserResponse updateUser(Long id, UserRequest userRequest) throws UserResponseException;

    Status deleteUser(Long id);

}
