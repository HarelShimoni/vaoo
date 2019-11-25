package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.SecurityService;
import com.finastra.vaoo.service.user.UserService;
import com.finastra.vaoo.web.model.response.LoginResponse;
import com.finastra.vaoo.web.model.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private String INVALID_TOKEN = "token is invalid: %s";
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id, @RequestHeader String token) {
        if (SecurityService.validate(token)) {
        return new ResponseEntity<>(userService.getUser(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString())), HttpStatus.OK);
        } else throw new SecurityException(String.format(INVALID_TOKEN, token));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        String token = userDto.getToken();

        if (SecurityService.validate(token)) {
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
        } else throw new SecurityException(String.format(INVALID_TOKEN, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id, @RequestHeader String token) {
        if (SecurityService.validate(token)) {
            userService.deleteUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } else throw new SecurityException(String.format(INVALID_TOKEN, token));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        String token = userDto.getToken();

        if (SecurityService.validate(token)) {
            UserDto updatedUser = userService.updateUser(userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else throw new SecurityException(String.format(INVALID_TOKEN, token));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginDetails) {
        String userId = loginDetails.get("userId");
        String password = loginDetails.get("password");

        if (userId == null || password == null) {
            return new ResponseEntity<>(new LoginResponse("user details for user id: " + userId + " could not be found", false), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.login(UUID.fromString(userId), password), HttpStatus.OK);

    }

}
