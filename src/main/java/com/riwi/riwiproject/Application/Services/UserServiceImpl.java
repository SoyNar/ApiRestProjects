package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Mapper.UserMapper;
import com.riwi.riwiproject.Application.Ports.in.IUserService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.UserRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.UserResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;
import com.riwi.riwiproject.domain.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // Guardar el usuario y mapearlo a UserResponseDto
        return userMapper.userToUserDto(userRepository.save(user));

    }
@Transactional
    @Override
    public UserResponseDto createUSerByAdmin(UserRequestDto userRequestDto) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Codificar la contraseña del nuevo usuario
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        // Mapear el DTO a la entidad User
        User user = userMapper.userDtoToUser(userRequestDto);

        // Asignar la contraseña encriptada
        user.setPassword(encodedPassword);

        // Asignar el rol al nuevo usuario
        user.setRole(userRequestDto.getRole()); // Esto debería venir del DTO, o puedes definirlo tú

        // Guardar el nuevo usuario y devolver el DTO de respuesta
        return userMapper.userToUserDto(userRepository.save(user));
    }

}
