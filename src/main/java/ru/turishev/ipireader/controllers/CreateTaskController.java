package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.services.DivisionsTopicService;
import ru.turishev.ipireader.services.TasksService;

import java.util.Arrays;

@Controller
public class CreateTaskController {
    @Autowired
    TasksService tasksService;
    @Autowired
    DivisionsTopicService divisionsTopicService;

    @GetMapping("/сreatetask")
    public String getTopics(Model model) {
        model.addAttribute("topics", divisionsTopicService.getRootDivisionTopic());
        return "createtasktopics";
    }

    @GetMapping("/createtask/{topicid}")
    public String getCreateTaskPage(@PathVariable Long topicid, Model model) {
        model.addAttribute("topicid",topicid);
        return "createtask";
    }

    @PostMapping("/createtask/{topicid}")
    public String getCreateTaskPage(@PathVariable Long topicid,
                                    @RequestParam String subject,
                                    @RequestParam String description,
                                    @RequestParam MultipartFile[] file,
                                    @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Long id = tasksService.createTask(topicid, subject, description, Arrays.asList(file), userDetailsImpl.getUser());
        if(id==-1) return "redirect:/createtask/"+topicid;
        return "redirect:/task/"+id;
    }
}
