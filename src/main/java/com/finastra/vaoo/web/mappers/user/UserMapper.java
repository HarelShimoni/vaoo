package com.finastra.vaoo.web.mappers.user;

import com.finastra.vaoo.domain.user.User;
import com.finastra.vaoo.web.model.user.UserDto;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMapper {
    User toEntity (UserDto userDto);
    UserDto toDto (User user);
}
