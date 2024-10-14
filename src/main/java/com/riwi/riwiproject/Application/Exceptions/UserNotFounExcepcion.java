package com.riwi.riwiproject.Application.Exceptions;

public class UserNotFounExcepcion extends RuntimeException{
    public UserNotFounExcepcion(String username) {
        super("User not found with username: " + username);    }
}
