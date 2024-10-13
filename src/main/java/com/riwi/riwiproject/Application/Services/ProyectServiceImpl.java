package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Ports.in.IProyectService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.TaskRequesDTo;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.IProyectsRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.ITaskRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;
import com.riwi.riwiproject.domain.Model.Proyects;
import com.riwi.riwiproject.domain.Model.Task;
import com.riwi.riwiproject.domain.Model.User;
import jakarta.persistence.UniqueConstraint;
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
        for (TaskRequesDTo taskRequesDTo : tasks) {

            // buscar usuario por nombre

            User users = userRepository.findByUsername(taskRequesDTo.getUserAsigned()).orElseThrow(() ->
                    new RuntimeException("user not found"));
            Task task = Task.builder()
                    .tittle(taskRequesDTo.getTittle())
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
    public List<Task> readAll() {
        return this.taskRepository.findAll();
    }
}
