package com.shiva.tims.models.Dtos.Project;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProjectRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
}
