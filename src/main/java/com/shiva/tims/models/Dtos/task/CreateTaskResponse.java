package com.shiva.tims.models.Dtos.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateTaskResponse {
	
	private String id;
	private String title;
	private String message;

}
