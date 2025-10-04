package dev.manu.taskmanager.dto;

import dev.manu.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TaskRequest(
        UUID id,
        @NotBlank(message = "The title cannot be empty.")
        String title,
        String description,
        TaskStatus status
) {
}
