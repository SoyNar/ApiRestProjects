package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Exceptions.UserNotFounExcepcion;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;

import com.riwi.riwiproject.domain.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

// clase para
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
  private UserRepository userRepository;


@Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFounExcepcion(username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
