package com.shiva.tims.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attachments")
@Data
@NoArgsConstructor
public class Attachment {
	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	@NotBlank
	@Column(name = "filename", nullable = false )
	private String fileName;
	
	@NotBlank
	@Column(name = "file_path", nullable = false)
	private String filePath;
	
	@ManyToOne
	@JoinColumn(name = "uploaded_by", nullable = false)
	private User uploadedBy;
	
	@Column(name = "uploaded_at", nullable = false, updatable = false)
	private LocalDateTime uploadedAt;
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
		this.uploadedAt = LocalDateTime.now();
	}
	
}
