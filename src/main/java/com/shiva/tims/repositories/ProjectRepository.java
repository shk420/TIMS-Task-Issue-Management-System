package com.shiva.tims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiva.tims.models.Project;

public interface ProjectRepository extends JpaRepository<Project, String>{

	boolean existsByName(String name);
	

}
