package com.shiva.tims.models.Dtos.task;
import java.time.LocalDateTime;

import com.shiva.tims.models.TaskPriority;
import com.shiva.tims.models.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponse {

    private final String id;
    private final String projectId;

    private final String title;
    private final String description;

    private final String assigneeId;
    private final String reporterId;

    private final TaskPriority priority;
    private final TaskStatus status;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
