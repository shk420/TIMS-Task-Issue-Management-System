package com.shiva.tims.models.Dtos.task;

import com.shiva.tims.models.TaskPriority;
import com.shiva.tims.models.TaskStatus;

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

    private final String createdAt;
    private final Strinfg updatedAt;

}
