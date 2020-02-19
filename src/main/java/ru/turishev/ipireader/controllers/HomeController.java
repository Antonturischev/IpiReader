package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.security.UserDetailsImpl;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    TasksDto tasksDto;

    @GetMapping
    public String getHomePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        Iterable<Task> tasks = tasksDto.getActiveTasksByUser(user.getUser());
        model.addAttribute("tasks",tasks);
        return "homepage";
    }
}
