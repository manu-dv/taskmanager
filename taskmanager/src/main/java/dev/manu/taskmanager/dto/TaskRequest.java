package dev.manu.taskmanager.dto;

import dev.manu.taskmanager.model.TaskStatus;

public record TaskRequest(
        String title,
        String description,
        TaskStatus status
) {
}
