package com.shiva.tims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.tims.models.Dtos.UserLoginDto;
import com.shiva.tims.models.Dtos.UserLoginResponseDto;
import com.shiva.tims.services.UserService;

@RestController
@RequestMapping("/Auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/Login")
	public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginDto dto){
		
				UserLoginResponseDto response = userService.login(dto.getUserName(),dto.getPassword());
		return ResponseEntity.ok(response);
		
	}
}
