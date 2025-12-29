package com.shiva.tims.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<?> handleWrongPassword(WrongPasswordException ex){
		
		return ResponseEntity.status(401).body(ex.getMessage());
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<?> handleDuplicateResource(DuplicateResourceException ex){
		return ResponseEntity.status(409).body(ex.getMessage());
	}
}
