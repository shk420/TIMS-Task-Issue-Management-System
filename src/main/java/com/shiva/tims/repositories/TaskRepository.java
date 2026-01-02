package com.shiva.tims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiva.tims.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
	boolean existsByTitle(String title);

}
