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
		
		if(repo.existsByTitleAndProject_Id(request.getTitle(), projectId)) {
			
			throw new DuplicateResourceException("Task with same name already exists : " + request.getTitle());
		}
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project does not exist:  " + projectId ));
		
		String assigneeId = request.getAssigneeId();
		User assignee = userRepo.findById(assigneeId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignee Does Not Exist : " + assigneeId));
		
		String reporterId = SecurityUtil.getCurrentUserId();
		User reporter = userRepo.findById(reporterId)
				.orElseThrow(() -> new ResourceNotFoundException("Reporter Does Not Exist:  " + reporterId));
		
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

	@Transactional(readOnly = true)
	public TaskResponse getTaskById(String projectId, String taskId){
		Task task = repo.findById(taskId)
			.orElseThrow (() -> new ResourceNotFoundException("Task Does Not exists : " + taskId));
		

		if(!task.getProject().getId().equals(projectId)){
			throw new ResourceNotFoundException("Task Does Not belong to this project : " + projectId );
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

	@Transactional(readOnly = true)
	public List<TaskList> getAllTasks(String projectId){

		if(!projectRepo.existsById(projectId)){
			throw new ResourceNotFoundException("Project does not exists");
		}
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



	@Transactional
	public void deleteTaskById(String projectId,String taskId){
		Task task = repo.findById(taskId)
		.orElseThrow(() -> new ResourceNotFoundException("Task does not exist :  " + taskId));
		if(!task.getProject().getId().equals(projectId)){
			throw new ResourceNotFoundException(" Task does not belong to this project : " + projectId );
		}
		repo.delete(task);
	}

	

}
