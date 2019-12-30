package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.service.UserService;
import com.gdziepotanczymy.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
