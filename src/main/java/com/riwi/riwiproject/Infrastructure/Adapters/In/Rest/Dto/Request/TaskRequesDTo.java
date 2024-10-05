package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public class TaskRequesDTo {
    @NotBlank(message = "El título no puede estar vacío")
    private String tittle;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    private Long proyectId;  // Relación con el Proyecto
    private Long userId;     // ID del usuario asignado
}
