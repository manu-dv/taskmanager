package dev.manu.taskmanager.service;

import dev.manu.taskmanager.model.TaskModel;
import dev.manu.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public TaskModel save(TaskModel task) {
        return taskRepository.save(task);
    }

    public void delete(TaskModel task) {
        taskRepository.delete(task);
    }
}
