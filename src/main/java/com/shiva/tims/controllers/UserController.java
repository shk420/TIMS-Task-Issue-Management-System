package com.shiva.tims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.tims.models.User;
import com.shiva.tims.models.Dtos.UserRegisterDto;
import com.shiva.tims.models.Dtos.UserResponseDto;
import com.shiva.tims.models.Dtos.UserUpdateDto;
import com.shiva.tims.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserResponseDto>> GetAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
		
	}
	
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto dto) {
		
		String userName = userService.registerUser(dto);
		
		return ResponseEntity.ok("User Registered Successfully with UserName : " + userName);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<String> updateUser( @RequestBody UserUpdateDto dto ){
		
		User updatedUser = userService.updateUser(dto);
		return ResponseEntity.ok("User Updated Successfully:  " + updatedUser.getUsername());
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username){
		
		userService.deleteUser(username);
		
		return ResponseEntity.ok("User wirth username: " + username +" is deleted" );
		
	}
	
	
	

}
