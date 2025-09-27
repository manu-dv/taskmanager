package dev.manu.taskmanager.dto;

import dev.manu.taskmanager.model.TaskStatus;

import java.util.UUID;

public record TaskResponse(
        UUID id,
        String title,
        String description,
        TaskStatus status
) {
}
