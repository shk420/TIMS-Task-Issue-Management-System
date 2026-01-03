package com.shiva.tims.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.tims.models.Dtos.task.CreateTaskRequest;
import com.shiva.tims.models.Dtos.task.CreateTaskResponse;
import com.shiva.tims.models.Dtos.task.TaskList;
import com.shiva.tims.models.Dtos.task.TaskResponse;
import com.shiva.tims.services.TaskService;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
	// DI
	private final TaskService service;
	public TaskController(TaskService service){
		this.service = service;
		
	}
	
	// Creating a new Task
	@PreAuthorize("hasRole('ADMIN','MANAGER','TESTER')")
	@PostMapping
	public ResponseEntity<CreateTaskResponse> createTask(@PathVariable String projectId,
			@Valid @RequestBody CreateTaskRequest request) {
		CreateTaskResponse response = service.createTask(projectId, request);
		return ResponseEntity
				.created(URI.create("/projects/" + projectId +"/tasks/" + response.getId()))
				.body(response);
		
	}

	// Get Task by ID
	@PreAuthorize("hasRole('ADMIN','MANAGER','DEVELOPER','TESTER')")
	@GetMapping("/{taskId}")
	public ResponseEntity<TaskResponse> getTaskById(@PathVariable String projectId, @PathVariable String taskId){

		TaskResponse response = service.getTaskById(projectId,taskId);

		return ResponseEntity.ok(response);

	}

	// Get All Tasks
	@PreAuthorize("hasRole('ADMIN','MANAGER', 'DEVELOPER','TESTER')")
	@GetMapping
	public ResponseEntity<List<TaskList>> getAllTasks(@PathVariable String projectId){
		List<TaskList> tasks = service.getAllTasks(projectId);

		return ResponseEntity.ok(tasks);
	}
	
	
	
	

}
