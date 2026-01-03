package com.shiva.tims.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.tims.models.Dtos.task.CreateTaskRequest;
import com.shiva.tims.models.Dtos.task.CreateTaskResponse;
import com.shiva.tims.services.TaskService;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
	
	private final TaskService service;
	public TaskController(TaskService service){
		this.service = service;
		
	}
	
	
	@PreAuthorize("hasRole('ADMIN','MANAGER')")
	@PostMapping
	public ResponseEntity<CreateTaskResponse> createTask(@PathVariable String projectId,
			@RequestBody CreateTaskRequest request) {
		CreateTaskResponse response = service.createTask(projectId, request);
		return ResponseEntity
				.created(URI.create("/projects/" + projectId +"/taskId/" + response.getId()))
				.body(response);
		
	}
	
	
	

}
