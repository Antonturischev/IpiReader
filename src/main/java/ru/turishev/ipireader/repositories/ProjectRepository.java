package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();
}
