package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest;

import com.riwi.riwiproject.Application.Ports.in.IProyectService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/proyect")
@RestController
public class ProyectController {

    @Autowired
    private IProyectService proyectService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProyectResponseDto> creatProyect(@RequestBody ProyectRequesDto proyectRequesDto) {
        ProyectResponseDto proyectResponseDto = this.proyectService.save(proyectRequesDto);

         return  ResponseEntity.status(HttpStatus.CREATED).body(proyectResponseDto);
    }
}
