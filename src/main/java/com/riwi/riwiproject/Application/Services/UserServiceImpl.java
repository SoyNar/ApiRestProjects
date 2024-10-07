package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Mapper.UserMapper;
import com.riwi.riwiproject.Application.Ports.in.IUserService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.UserRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.UserResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;
import com.riwi.riwiproject.domain.Enums.Role;
import com.riwi.riwiproject.domain.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    @Override
    public List<User> readAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    @Override
    public UserResponseDto save(UserRequestDto userDto) {



        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        // Crear un nuevo usuario a partir del DTO, pasando solo el DTO
        User user = userMapper.userDtoToUser(userDto); // Aquí solo pasas el DTO

        // Asignar la contraseña encriptada
        user.setPassword(encodedPassword);
        user.setFirstName(userDto.getName());


        // Guardar el usuario y mapearlo a UserResponseDto
        return userMapper.userToUserDto(userRepository.save(user));

    }
}
