package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;

import com.riwi.riwiproject.domain.Model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class TaskRequesDTo {

    private String asignedUsername;
    @NotBlank
    private String tittle;
    @NotBlank
    private String description;
    @NotNull
    private String userAsigned;
}
