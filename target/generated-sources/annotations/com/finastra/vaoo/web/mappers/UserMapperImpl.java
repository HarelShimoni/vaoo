package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.domain.User.UserBuilder;
import com.finastra.vaoo.web.model.UserDto;
import com.finastra.vaoo.web.model.UserDto.UserDtoBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-21T00:00:02+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_162 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.phone( userDto.getPhone() );
        user.email( userDto.getEmail() );
        user.city( userDto.getCity() );
        user.country( userDto.getCountry() );

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phone( user.getPhone() );
        userDto.email( user.getEmail() );
        userDto.city( user.getCity() );
        userDto.country( user.getCountry() );

        return userDto.build();
    }
}
