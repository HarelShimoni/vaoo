package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.web.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper (componentModel = "spring")
public interface UserMapper {
    User toEntity (UserDto userDto);
    UserDto toDto (User user);
}
