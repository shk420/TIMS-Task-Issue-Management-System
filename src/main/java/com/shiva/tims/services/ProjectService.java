package com.shiva.tims.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiva.tims.Exceptions.DuplicateResourceException;
import com.shiva.tims.Exceptions.ResourceNotFoundException;
import com.shiva.tims.Utils.SecurityUtil;
import com.shiva.tims.models.Project;
import com.shiva.tims.models.User;
import com.shiva.tims.models.Dtos.Project.AddProjectRequest;
import com.shiva.tims.models.Dtos.Project.AddProjectResponse;
import com.shiva.tims.repositories.ProjectRepository;
import com.shiva.tims.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // CREATE
    @Transactional
    public AddProjectResponse addProject(AddProjectRequest request) {

        if (projectRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException(
                "Project already exists with name: " + request.getName()
            );
        }

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());

        User creator =
            userRepository.getReferenceById(SecurityUtil.getCurrentUserId());
        project.setCreatedBy(creator);

        Project saved = projectRepository.save(project);

        return mapToResponse(saved);
    }

    // READ ALL
    public List<AddProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ BY ID
    public AddProjectResponse getProjectById(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found: " + id));

        return mapToResponse(project);
    }

    // DELETE
    @Transactional
    public void deleteProject(String id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found: " + id));

        projectRepository.delete(project);
    }

    // ENTITY → DTO
    private AddProjectResponse mapToResponse(Project project) {
        return new AddProjectResponse(
                project.getId(),
                project.getName(),
                project.getCreatedAt()
        );
    }
}
