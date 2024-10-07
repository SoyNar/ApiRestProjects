package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Ports.in.IProyectService;
import com.riwi.riwiproject.Config.MailSender;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.TaskRequesDTo;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.TaskUserAsignedRequestDTo;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.IProyectsRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.ITaskRepository;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.UserRepository;
import com.riwi.riwiproject.domain.Enums.Role;
import com.riwi.riwiproject.domain.Excepcions.ResourceNotFoundException;
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

    @Autowired
    private MailSender emailService;

    @Transactional
    @Override
    public ProyectResponseDto save(ProyectRequesDto proyectRequesDto) {
        // Guardar el proyecto
        Proyects proyect = saveProject(proyectRequesDto);

        // Guardar las tareas asociadas
        List<Task> tasks = saveTasks(proyectRequesDto.getTasks(), proyect);

        // Construir la respuesta
        return mapToResponseDto(proyect, tasks);
    }

    private Proyects saveProject(ProyectRequesDto proyectRequesDto) {
        Proyects proyect = Proyects.builder()
                .tittle(proyectRequesDto.getTitle())
                .description(proyectRequesDto.getDescription())
                .nameAdmin(proyectRequesDto.getNameAdmin())
                .build();
        return proyectsRepository.save(proyect);
    }


    private List<Task> saveTasks(List<TaskUserAsignedRequestDTo> taskRequesDtos, Proyects proyect) {
        if (taskRequesDtos == null) {
            return new ArrayList<>();
        }

        return taskRequesDtos.stream()
                .map(taskRequest -> createAndSaveTask(taskRequest, proyect))
                .collect(Collectors.toList());
    }


    private Task createAndSaveTask(TaskUserAsignedRequestDTo taskRequest, Proyects proyect) {
        User assignedUser = userRepository.findByUsername(taskRequest.getNameAssigned())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + taskRequest.getNameAssigned()));

        // Verificar que el usuario tenga el rol "USER"
        if (!assignedUser.getRole().equals(Role.USER)) {
            throw new IllegalArgumentException("Solo los usuarios con el rol 'USER' pueden ser asignados a tareas.");
        }
        Task task = Task.builder()
                .tittle(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .proyect(proyect)
                .userAsigned(assignedUser)
                .build();


        // Enviar notificación por correo al usuario asignado
        String destinatario = assignedUser.getUsername();
        String asunto = "Nueva tarea asignada: " + task.getTittle();
        String mensaje = String.format("Hola %s,\n\nSe te ha asignado una nueva tarea: %s.\n\nDescripción: %s",
                assignedUser.getUsername(), task.getTittle(), task.getDescription());
        emailService.enviarCorreo(destinatario, asunto, mensaje);

        return taskRepository.save(task);
    }

    private ProyectResponseDto mapToResponseDto(Proyects proyect, List<Task> tasks) {
        return ProyectResponseDto.builder()
                .title(proyect.getTittle())
                .description(proyect.getDescription())
                .nameAdmin(proyect.getNameAdmin())
                .task(tasks.stream()
                        .map(this::mapToTaskResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private TaksResponseDto mapToTaskResponseDto(Task task) {
        return TaksResponseDto.builder()
                .title(task.getTittle())
                .description(task.getDescription())
                .userIdAsigned(task.getUserAsigned().getUsername())
                .build();
    }
}
