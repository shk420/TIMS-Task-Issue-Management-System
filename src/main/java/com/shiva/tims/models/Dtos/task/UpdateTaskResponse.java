package com.shiva.tims.models.Dtos.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTaskResponse {

    private String id;
	private String title;
	private String message;

}
