package com.shiva.tims.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.tims.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private final TaskService service;
	
	@PreAuthorize("hasRole('ADMIN','MANAGER')")
	@PostMapping
	public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest) {
		Task task = service.
	}
	
	
	

}
