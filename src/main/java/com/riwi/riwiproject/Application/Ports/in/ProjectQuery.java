package com.riwi.riwiproject.Application.Ports.in;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;

import java.util.List;

public interface ProjectQuery {
    ProyectResponseDto createProyectWithTasks(ProyectRequestDto proyectRequestDTO);
    // Métodos para consulta
    List<ProyectResponseDto> getAllProyects();
    ProyectResponseDto getProyectById(Long id);
}
