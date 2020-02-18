package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.DivisionsTopicRepository;
import ru.turishev.ipireader.repositories.TasksRepository;
import ru.turishev.ipireader.repositories.UsersRepository;
import ru.turishev.ipireader.model.User;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    DivisionsTopicRepository divisionsTopicRepository;
    @Autowired
    TasksRepository tasksRepository;

    @GetMapping
    public String getHomePage() {
        return "homepage";
    }
}
