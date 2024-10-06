package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response;

import com.riwi.riwiproject.domain.Enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String password;
    private Role role;
}
