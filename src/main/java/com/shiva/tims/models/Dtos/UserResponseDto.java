package com.shiva.tims.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
	
	private String username;
	private String fullName;
	private String email;
	private String role;

}
