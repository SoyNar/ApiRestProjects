package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAsignedRequestDto {
    @NotBlank(message = "el nombre del usuario no puede ser null")
    private String name;
    private String username;
}
