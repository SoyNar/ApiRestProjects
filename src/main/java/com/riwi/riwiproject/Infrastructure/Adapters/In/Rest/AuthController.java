package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest;
import com.riwi.riwiproject.Application.Ports.in.IUserService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.AuthRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.UserRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.UserResponseDto;
import com.riwi.riwiproject.Infrastructure.Security.Configuration.JWTUtils;
import com.riwi.riwiproject.domain.Model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;


    @PostMapping("/register")
    @Operation(
            summary = "Create a new user.",
            description = "Allows the registration of a new user by providing the necessary details.")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto) {
        try {
            UserResponseDto createdUser = userService.save(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/login")
    @Operation(
            summary = "Authenticate a user.",
            description = "Provides a user's data to generate a token that allows them to use private endpoints if their role allows it.")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        String token = jwtUtils.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAll (){
        List<User> users = this.userService.readAll();
        return  ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/createUsers")
    @PreAuthorize("'ADMIN'")
    public ResponseEntity<UserResponseDto> createByAdmin( @RequestBody UserRequestDto userRequestDto){
        try {
            UserResponseDto createdUser = userService.save(userRequestDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}