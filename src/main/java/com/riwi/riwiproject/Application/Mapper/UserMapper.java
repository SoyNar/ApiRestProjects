package com.riwi.riwiproject.Application.Mapper;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.UserRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.UserResponseDto;
import com.riwi.riwiproject.domain.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto userToUserDto(User user) {
        return UserResponseDto.builder()
                .password(user.getPassword())
                .name(user.getFirstName())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public User userDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .firstName(userRequestDto.getName())
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .role(userRequestDto.getRole())
                .build();
    }

}
