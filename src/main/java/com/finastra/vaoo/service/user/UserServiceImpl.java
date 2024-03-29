package com.finastra.vaoo.service.user;

import com.finastra.vaoo.domain.user.User;
import com.finastra.vaoo.repository.UserRepository;
import com.finastra.vaoo.web.mappers.user.UserMapper;
import com.finastra.vaoo.web.model.response.LoginResponse;
import com.finastra.vaoo.web.model.user.UserDto;
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
        return userRepository.findById(userId).map(user -> userMapper.toDto(user));
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User savedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));


    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public LoginResponse login(UUID userId, String password) {
        LoginResponse loginResponse = new LoginResponse();

        Boolean authenticated = userRepository.findById(userId)
                .map(u -> u.getPassword().equals(password)).orElse(false);

        loginResponse.setAuthenticated(authenticated);
        return loginResponse;
    }
}
