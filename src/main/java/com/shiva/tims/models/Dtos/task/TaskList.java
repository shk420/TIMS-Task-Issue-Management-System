package com.shiva.tims.models.Dtos.task;

import com.shiva.tims.models.TaskPriority;
import com.shiva.tims.models.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskList {

    private final String id;
    private final String title;
    private final String assigneeId;
    private final TaskStatus status;
    private final TaskPriority priority;

}
