package com.assignment.controller;

import com.assignment.dto.request.UserLoginRequest;
import com.assignment.dto.response.UserLogInResponse;
import com.assignment.exceptionHandler.UserResponseException;
import com.assignment.repository.UserRepository;
import com.assignment.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public UserLogInResponse userLogIn(@RequestBody UserLoginRequest userLogInRequest) throws UserResponseException {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogInRequest.getEmail(), userLogInRequest.getPassword()));
            var user = userRepository.findUserByEmail(userLogInRequest.getEmail()).orElseThrow(() ->
                    new UserResponseException("Invalid email or Password"));
            var jwt = jwtUtil.generateToken(user);
            UserLogInResponse userLogInResponse = new UserLogInResponse();
            userLogInResponse.setToken(jwt);
            return userLogInResponse;
    }
}
