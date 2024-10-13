package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest;

import com.riwi.riwiproject.Application.Ports.in.ITaskService;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.domain.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("/find")
    @PreAuthorize("'USER'")
    public ResponseEntity<List<TaksResponseDto>> findTaskByUser(@RequestParam(required = false) String userAsigned) {
        List<TaksResponseDto> tasks = taskService.readByUsernameAsigned(userAsigned);

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}
