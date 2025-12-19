package com.shiva.tims.models;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Users")
public class User {
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(unique= true, nullable = false)
	private String username;
	
	private String passwordHash;
	
	private String fullName;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	
	private LocalDateTime createdAt = LocalDateTime.now();
	

}
