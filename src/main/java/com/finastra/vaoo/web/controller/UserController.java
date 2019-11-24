package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.user.UserService;
import com.finastra.vaoo.web.model.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
        return new ResponseEntity<>(userService.getUser(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId.toString())), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
