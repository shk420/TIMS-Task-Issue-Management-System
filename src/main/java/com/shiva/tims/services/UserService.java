package com.shiva.tims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shiva.tims.Exceptions.UserNotFoundException;
import com.shiva.tims.Exceptions.WrongPasswordException;
import com.shiva.tims.Utils.JWTUtil;
import com.shiva.tims.models.Roles;
import com.shiva.tims.models.User;
import com.shiva.tims.models.Dtos.UserLoginResponseDto;
import com.shiva.tims.models.Dtos.UserRegisterDto;
import com.shiva.tims.models.Dtos.UserResponseDto;
import com.shiva.tims.models.Dtos.UserUpdateDto;
import com.shiva.tims.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;

	public String registerUser(UserRegisterDto dto) {
		if(userRepository.existsByUsername(dto.getUserName())) {
			throw new RuntimeException("User already Exits");
		}
		var user = new User();
		user.setUsername(dto.getUserName());
		user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
		user.setRole(Roles.DEVELOPER);
		user.setFullName(dto.getFullName());
		user.setEmail(dto.getEmail());
		userRepository.save(user);
		
		return user.getUsername();
	}

	public List<UserResponseDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> new UserResponseDto(
						user.getUsername(),
						user.getFullName(),
						user.getEmail(),
						user.getRole().name()))
				.toList();
	}

	public User updateUser(String username,UserUpdateDto dto) {

	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User with username '" + username + "' does not exist"));

	    if (dto.getFullName() != null) user.setFullName(dto.getFullName());
	    if (dto.getEmail() != null) user.setEmail(dto.getEmail());

	    return userRepository.save(user);
	}

	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username)
		        .orElseThrow(() -> new RuntimeException("User '" + username + "' does not exist"));

		userRepository.delete(user);
	}

	public UserLoginResponseDto login(String username, String password) {
		User user = userRepository.findByUsername(username)
		        .orElseThrow(() -> new UserNotFoundException("User Not Found with username :" + username));

		if(!passwordEncoder.matches(password, user.getPasswordHash())) {
			throw new WrongPasswordException("Wrong Password");
		}
		
		String token = jwtUtil.generateToken(
				
				user.getUsername(),
				user.getRole().name()
				);
		
		
		return new UserLoginResponseDto(
				
				user.getUsername(),
				user.getRole().name(),
				token);
	}
}
