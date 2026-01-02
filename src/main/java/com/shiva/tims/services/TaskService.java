package com.shiva.tims.services;

import org.springframework.stereotype.Service;

import com.shiva.tims.Exceptions.DuplicateResourceException;
import com.shiva.tims.models.Dtos.task.CreateTaskRequest;
import com.shiva.tims.models.Dtos.task.CreateTaskResponse;
import com.shiva.tims.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository repo;
	
	public CreateTaskResponse createTask(CreateTaskRequest request) {
		
		if(repo.existsByTitle(request.getTitle())) {
			
			throw new DuplicateResourceException("Task with saem")
		}
		
		
		
		
		
		
		
		
		
		
	}

}
