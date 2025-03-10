package com.example.resolver.user.controller;

import com.example.resolver.auth.annotation.Auth;
import com.example.resolver.auth.dto.AuthUser;
import com.example.resolver.user.dto.UserRequestDto;
import com.example.resolver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/users")
    public void update(
            @Auth AuthUser authUser,
            @RequestBody UserRequestDto request
    ) {
        userService.update(authUser, request);
    }
}
