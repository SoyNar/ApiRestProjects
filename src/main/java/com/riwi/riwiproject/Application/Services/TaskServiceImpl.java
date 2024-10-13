package com.riwi.riwiproject.Application.Services;

import com.riwi.riwiproject.Application.Ports.in.ITaskService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence.ITaskRepository;
import com.riwi.riwiproject.domain.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

     @Autowired
    private ITaskRepository taskRepository;

    public List<Task> getAllTasksOrdered() {
        Task taskEntity = new Task();
        TaksResponseDto.builder()
                .title(taskEntity.getTittle())
                .description(taskEntity.getDescription())
                .build();

        return taskRepository.findAllTasksOrderedByUserAsigned();
    }

    @Transactional
    @Override
    public List<TaksResponseDto> readByUsernameAsigned(String username) {
        List<Task> tasks;

        if (username == null || username.isEmpty()) {
            tasks = getAllTasksOrdered();
        } else {
            tasks = taskRepository.findTaskByUserAsigned(username);
        }

        // Convertir la lista de Task a TaskResponseDTO
        return tasks.stream()
                .map(task -> new TaksResponseDto(task.getTittle(),task.getDescription(),task.getUserAsigned().getUsername()))
                .collect(Collectors.toList());
    }

}
