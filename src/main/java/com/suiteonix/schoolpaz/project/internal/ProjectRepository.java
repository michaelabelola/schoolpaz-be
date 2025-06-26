package com.suiteonix.schoolpaz.project.internal;

import com.suiteonix.schoolpaz.project.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, ProjectId> {
    boolean existsById_Id(String idId);
}