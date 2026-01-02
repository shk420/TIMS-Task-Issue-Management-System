  package com.shiva.tims.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
public class Task {

	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="project_id", nullable = false)
	private Project project;
	
	@NotBlank
	@Column(nullable = false)
	private String title;
	
	@NotBlank
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name ="assignee_id", nullable = false )
	private User assignee;
	
	@ManyToOne
	@JoinColumn(name = "reporter_id", nullable = false)
	private User reporter;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskPriority priority;
	
	@Column(name ="created_at" , nullable = false, updatable = false )
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		if(this.status == null) {
		this.status = TaskStatus.OPEN;
		}
		if (this.priority == null) {
		this.priority= TaskPriority.MEDIUM;
		}
	}
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
