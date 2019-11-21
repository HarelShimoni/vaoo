package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.web.model.UserDto;
import com.finastra.vaoo.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE )
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);

    }
}
