package com.shiva.tims.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponseDto {
	
	private String username;
	private String role;
	private String token; 

}
