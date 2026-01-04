package com.shiva.tims.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.shiva.tims.models.Dtos.task.CreateTaskRequest;
import com.shiva.tims.models.Dtos.task.CreateTaskResponse;
import com.shiva.tims.models.Dtos.task.TaskList;
import com.shiva.tims.models.Dtos.task.TaskResponse;
import com.shiva.tims.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Create Task
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TESTER')")
    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(
            @PathVariable String projectId,
            @Valid @RequestBody CreateTaskRequest request) {

        CreateTaskResponse response = service.createTask(projectId, request);

        return ResponseEntity
                .created(URI.create("/projects/" + projectId + "/tasks/" + response.getId()))
                .body(response);
    }

    // Get Task by ID
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','DEVELOPER','TESTER')")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable String projectId,
            @PathVariable String taskId) {

        return ResponseEntity.ok(
                service.getTaskById(projectId, taskId)
        );
    }

    // Get All Tasks
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','DEVELOPER','TESTER')")
    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTasks(
            @PathVariable String projectId) {

        return ResponseEntity.ok(
                service.getAllTasks(projectId)
        );
    }

    // Delete Task
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(
            @PathVariable String projectId,
            @PathVariable String taskId) {

        service.deleteTaskById(projectId, taskId);
        return ResponseEntity.noContent().build();
    }
}
