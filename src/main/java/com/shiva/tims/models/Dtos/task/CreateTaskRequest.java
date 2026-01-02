package com.shiva.tims.models.Dtos.task;

import com.shiva.tims.models.TaskPriority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateTaskRequest {
	
	@NotBlank
	private String proejctId;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String assigneeId;
	
	private TaskPriority priority;

}
