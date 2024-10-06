package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
