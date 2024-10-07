package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProyectRequesDto {
    @NotBlank(message = "el titulo no puede ser null")
    private String title;
    @NotBlank(message = "la descripcion no puede ser nula")
    private String description;
    @NotBlank(message = "campo no puede ser null")
    private String nameAdmin;
    @NotNull(message = "la lista no puede ser vacia")
    private List<TaskRequesDTo> tasks;

}
