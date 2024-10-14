package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Exceptions.ProyectNoFoundException;
import com.riwi.riwiproject.Application.Exceptions.UserNotFounExcepcion;
import com.riwi.riwiproject.Application.Ports.in.IProyectService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.TaskUserAsignedRequestDTo;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.IProyectsRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.ITaskRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;
import com.riwi.riwiproject.domain.Model.Proyects;
import com.riwi.riwiproject.domain.Model.Task;
import com.riwi.riwiproject.domain.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyectServiceImpl implements IProyectService {

    @Autowired
    private IProyectsRepository proyectsRepository;

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public ProyectResponseDto save(ProyectRequesDto proyectRequesDto) {
        Proyects proyects = Proyects.builder()
                .tittle(proyectRequesDto.getTitle())
                .description(proyectRequesDto.getDescription())
                .nameAdmin(proyectRequesDto.getNameAdmin())
                .build();
        this.proyectsRepository.save(proyects);

    List<Task> tasksList = new ArrayList<>();


    Optional.ofNullable(proyectRequesDto.getTasks()).ifPresent(tasks -> {
        for (TaskUserAsignedRequestDTo taskRequesDTo : tasks) {

            // buscar usuario por nombre

            User users = userRepository.findByUsername(taskRequesDTo.getNameAssigned()).orElseThrow(() ->
                    new UserNotFounExcepcion(taskRequesDTo.getNameAssigned()));
            Task task = Task.builder()
                    .tittle(taskRequesDTo.getTitle())
                    .description(taskRequesDTo.getDescription())
                    .userAsigned(users)
                    .proyect(proyects)
                    .build();
            this.taskRepository.save(task);
            tasksList.add(task); // Añade la tarea a la lista
        }
    });

    proyects.setTasks(tasksList);


    return ProyectResponseDto.builder()
                .title(proyects.getTittle())
                .description(proyects.getDescription())
                .nameAdmin(proyectRequesDto.getNameAdmin())
                .task(proyects.getTasks().stream()
                        .map(task -> TaksResponseDto.builder()
                                .title(task.getTittle())
                                .description(task.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Proyects> readAll() {
        return this.proyectsRepository.findAll();
    }



    @Override
    public ProyectResponseDto disable(Long id) {
        Proyects existingProyect = proyectsRepository.findById(id)
                .orElseThrow(() -> new ProyectNoFoundException(id));

        // Lógica para deshabilitar o eliminar el proyecto
        proyectsRepository.delete(existingProyect);

        // Convertir el objeto eliminado a ProyectResponseDto y devolverlo
        return convertToResponseDto(existingProyect);
    }

    private ProyectResponseDto convertToResponseDto(Proyects proyect) {
        return ProyectResponseDto.builder()
                .title(proyect.getTittle())
                .description(proyect.getDescription())
                .task(proyect.getTasks().stream()  // Recorre todas las tareas del proyecto
                        .map(this::convertToTaskResponseDto)  // Convierte cada tarea a TaskResponseDto
                        .collect(Collectors.toList()))  // Colecciona los DTOs en una lista
                .build();
    }

    private TaksResponseDto convertToTaskResponseDto(Task task) {
        return TaksResponseDto.builder()
                .title(task.getTittle())
                .description(task.getDescription())
                .user(task.getUserAsigned().getUsername())  // Convierte el usuario de la tarea a UserResponseDto
                .build();


    }

}

