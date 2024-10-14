package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest;

import com.riwi.riwiproject.Application.Ports.in.IProyectService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.domain.Model.Task;
import com.riwi.riwiproject.domain.Model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/proyect")
@Tag(name = "Proyecto", description = "API de gestión de proyectos")
@RestController
public class ProyectController {

    @Autowired
    private IProyectService proyectService;


    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear un nuevo proyecto", security = @SecurityRequirement(name = "bearer-jwt"))

    public ResponseEntity<ProyectResponseDto> creatProyect(@RequestBody ProyectRequesDto proyectRequesDto) {
        ProyectResponseDto proyectResponseDto = this.proyectService.save(proyectRequesDto);

         return  ResponseEntity.status(HttpStatus.CREATED).body(proyectResponseDto);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("'ADMIN'")
    public ResponseEntity<ProyectResponseDto> deletePRoyect(@PathVariable Long id){
        ProyectResponseDto responseDto = proyectService.disable(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
