package com.shiva.tims.models.Dtos.Project;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddProjectResponse {
	
	private final String id;
	private final String name;
	private final LocalDateTime createdAt;

}
