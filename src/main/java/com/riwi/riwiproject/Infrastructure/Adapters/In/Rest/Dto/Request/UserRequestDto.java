package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;


import com.riwi.riwiproject.domain.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserRequestDto {

    private String username;
    private String password;
    private Role role;
}
