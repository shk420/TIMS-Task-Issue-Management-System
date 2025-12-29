package com.shiva.tims.controllers;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.shiva.tims.models.Dtos.Project.AddProjectRequest;
import com.shiva.tims.models.Dtos.Project.AddProjectResponse;
import com.shiva.tims.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // CREATE PROJECT
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AddProjectResponse> createProject(
            @Valid @RequestBody AddProjectRequest request) {

        AddProjectResponse response =
                projectService.addProject(request);

        return ResponseEntity
                .created(URI.create("/projects/" + response.getId()))
                .body(response);
    }

    // GET ALL PROJECTS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AddProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(
                projectService.getAllProjects());
    }

    // GET PROJECT BY ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AddProjectResponse> getProjectById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                projectService.getProjectById(id));
    }

    // DELETE PROJECT
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable String id) {

        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
