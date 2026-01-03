package com.shiva.tims.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shiva.tims.Exceptions.DuplicateResourceException;
import com.shiva.tims.Exceptions.ResourceNotFoundException;
import com.shiva.tims.Utils.SecurityUtil;
import com.shiva.tims.models.Project;
import com.shiva.tims.models.Task;
import com.shiva.tims.models.User;
import com.shiva.tims.models.Dtos.task.CreateTaskRequest;
import com.shiva.tims.models.Dtos.task.CreateTaskResponse;
import com.shiva.tims.models.Dtos.task.TaskResponse;
import com.shiva.tims.repositories.ProjectRepository;
import com.shiva.tims.repositories.TaskRepository;
import com.shiva.tims.repositories.UserRepository;

@Service
public class TaskService {
	private final TaskRepository repo;
	private final ProjectRepository projectRepo;
	private final UserRepository userRepo;
	
	public TaskService(TaskRepository repo, ProjectRepository projectRepo, UserRepository userRepo ) {
		this.repo = repo;
		this.projectRepo = projectRepo;
		this.userRepo = userRepo;
	}

	
	@Transactional
	public CreateTaskResponse createTask(String projectId, CreateTaskRequest request) {
		
		if(repo.existsByTitle(request.getTitle())) {
			
			throw new DuplicateResourceException("Task with same already exists");
		}
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project does not exist"));
		
		User assignee = userRepo.findById(request.getAssigneeId())
				.orElseThrow(() -> new ResourceNotFoundException("Assignee Does Not Exist"));
		
		String repoterId = SecurityUtil.getCurrentUserId();
		User reporter = userRepo.findById(repoterId)
				.orElseThrow(() -> new ResourceNotFoundException("Reporter Does Not Exist"));
		
		Task task = new Task();
		task.setProject(project);
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setAssignee(assignee);
		task.setReporter(reporter);
		task.setPriority(request.getPriority());
		
		Task saved = repo.save(task);
		
		return new CreateTaskResponse(
				saved.getId(), 
				saved.getTitle(), 
				"Task Created Successfully"
		);
		
		
	}

	public TaskResponse getTaskById(String projectId, String taskId){
		Task task = repo.existsById(taskId)
			.orElseThrow (() -> new ResourceNotFoundException("Task Does Not exists"));
		

		if(!task.getProject().getId().equals(projectId)){
			throw new ResourceNotFoundException("Task Does Not belong to this proejct");
		}

		return new TaskResponse(
		task.getId(),
		task.getProject().getId(),
		task.getTitle(),
		task.getDescription(),
		task.getAssignee().getId(),
		task.getReporter().getId(),
		task.getStatus(),
		task.getPriority(),
		task.getCreatedAt(),
		task.getUpdatedAt()
		);
	}

	public List<TaskList> getAllTasks(String projectId){
		List<Task> tasks =  repo.findByProjectId(projectId);

		return tasks.stream()
		.map(task -> new TaskList(
			task.getId(),
			task.getTitle(),
			task.getAssignee().getId(),
			task.getStatus(),
			task.getPriority()
		))
		.toList();
	}

}
