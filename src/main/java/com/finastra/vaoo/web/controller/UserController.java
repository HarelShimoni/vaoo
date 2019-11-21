package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.web.model.UserDto;
import com.finastra.vaoo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{userId}")
    public UserDto getUser(@PathVariable UUID userId) {
        return userService.getUser(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId.toString()));
    }
}
