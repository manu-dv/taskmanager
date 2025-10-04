package dev.manu.taskmanager.controller;

import dev.manu.taskmanager.dto.TaskRequest;
import dev.manu.taskmanager.dto.TaskResponse;
import dev.manu.taskmanager.model.TaskModel;
import dev.manu.taskmanager.model.TaskStatus;
import dev.manu.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse findById(@PathVariable UUID id) {
        TaskModel task = taskService.findById(id);

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@RequestBody TaskRequest request) {
        TaskModel task = new TaskModel();
        task.setTitle(request.title());
        task.setDescription(request.description() == null ? "" : request.description());
        task.setStatus(request.status() == null ? TaskStatus.IN_PROCESS : request.status());

        TaskModel taskSaved = taskService.save(task);

        return new TaskResponse(
                taskSaved.getId(),
                taskSaved.getTitle(),
                taskSaved.getDescription(),
                taskSaved.getStatus()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        TaskModel task = taskService.findById(id);
        taskService.delete(task);
    }
}
