package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.services.TasksService;


@Controller
public class HomeController {

    @Autowired
    private TasksService tasksService;
    
    @GetMapping("/createdbymy")
    public String getCreatedByMyTasks(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
    	Page<TasksDto> tasks = tasksService.getCreatedByMeTasks(user.getUser(), pageable);
        if(tasks.getTotalPages()!=0) {
        	model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/createdbymy");
    	return "homepage";
    }
    
    @GetMapping("/")
    public String getHomePage(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TasksDto> tasks = tasksService.getActiveTasksByUser(user.getUser(),pageable);
        if(tasks.getTotalPages()!=0) {
        	model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/");
        return "homepage";
    }

}
