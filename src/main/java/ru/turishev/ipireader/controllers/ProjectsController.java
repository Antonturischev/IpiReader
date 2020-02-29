package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.services.ProjectService;
import ru.turishev.ipireader.services.TasksService;


import java.util.List;

@Controller
public class ProjectsController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TasksService tasksService;

    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<Project> projects = projectService.getProjects();
        if(!projects.isEmpty()) {
            model.addAttribute("projects", projects);
        }
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String getProject(@PathVariable Long id, Model model, Pageable pageable) {
        Page<TasksDto> tasks = tasksService.getTasksByProject(id, pageable);
        if(tasks.getTotalPages()!=0) {
            model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/projects/"+id.toString());
        model.addAttribute("dlm","?");
        return "project";
    }
}
