package com.shiva.tims.models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {
	
	@NotBlank
	private String userName;
	@NotBlank
	@Size(min=8)
	private String password;
	@NotBlank
	private String fullName;
	@Email
	@NotBlank
	private String email;
	

}
