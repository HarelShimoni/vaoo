package com.finastra.vaoo.service;

import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.web.mappers.UserMapper;
import com.finastra.vaoo.web.model.UserDto;
import com.finastra.vaoo.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDto> getUser(UUID userId) {
        return Optional.of(userMapper.userToUserDto(userRepository.findById(userId).get()));
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }
}
