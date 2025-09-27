package dev.manu.taskmanager.controller;

import dev.manu.taskmanager.dto.TaskRequest;
import dev.manu.taskmanager.dto.TaskResponse;
import dev.manu.taskmanager.model.TaskModel;
import dev.manu.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // TODO: Mover a una clase Mapper todas las conversiones a TaskResponse
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> findAll() {
        return taskService.findAll()
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                )).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@RequestBody TaskRequest request) {
        TaskModel task = new TaskModel();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());

        TaskModel taskSaved = taskService.save(task);

        return new TaskResponse(
                taskSaved.getId(),
                taskSaved.getTitle(),
                taskSaved.getDescription(),
                taskSaved.getStatus()
        );
    }

}
