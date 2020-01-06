package com.gdziepotanczymy.controller;

import com.gdziepotanczymy.controller.exception.AlreadyExists;
import com.gdziepotanczymy.controller.exception.BadRequest;
import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.service.UserService;
import com.gdziepotanczymy.service.dto.CreateUpdateUserDto;
import com.gdziepotanczymy.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) throws NotFound {
        return userService.getUserById(id);
    }

    @PostMapping()
    public UserDto newUser(@RequestBody CreateUpdateUserDto createUpdateUserDto) throws AlreadyExists, BadRequest {
        return userService.createUser(createUpdateUserDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUserById(@PathVariable Long id, @RequestBody CreateUpdateUserDto createUpdateUserDto) throws NotFound, BadRequest, AlreadyExists {
        return userService.updateUserById(id, createUpdateUserDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUserById(@PathVariable Long id) throws NotFound {
        return userService.deleteUserById(id);
    }
}
