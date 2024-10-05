package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;


import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ProyectRequestDto {

    @NotBlank(message = "El título no puede estar vacío")
    private String tittle;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotBlank(message = "El nombre del administrador no puede estar vacío")
    private String nameAdmin;

    private List<Long> taskIds;  // IDs de tareas asociadas
}
