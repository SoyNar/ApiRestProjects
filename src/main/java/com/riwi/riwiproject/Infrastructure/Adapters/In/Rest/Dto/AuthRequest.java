package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
                          @NotBlank  String userName,
                          @NotBlank String password) {
}
