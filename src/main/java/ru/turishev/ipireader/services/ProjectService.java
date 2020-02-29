package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.repositories.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }
}
