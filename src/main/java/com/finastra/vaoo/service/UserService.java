package com.finastra.vaoo.service;

import com.finastra.vaoo.web.model.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<UserDto> getUser(UUID userId);
    List<UserDto> getUsers();
    UserDto createUser(UserDto userDto);
    void deleteUser(UUID userId);


}
