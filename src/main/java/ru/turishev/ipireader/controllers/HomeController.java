package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.services.TasksService;
import ru.turishev.ipireader.services.UserService;


@Controller
public class HomeController {

    @Autowired
    private TasksService tasksService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/createdbymy")
    public String getCreatedByMyTasks(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
    	Page<TasksDto> tasks = tasksService.getCreatedByMeTasks(user.getUser(), pageable);
        if(tasks.getTotalPages()!=0) {
        	model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/createdbymy");
        model.addAttribute("dlm","?");
    	return "homepage";
    }
    
    @GetMapping("/completedbyme")
    public String getCompleteByMyTasks(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
    	Page<TasksDto> tasks = tasksService.getCompleteByMeTasks(user.getUser(), pageable);
        if(tasks.getTotalPages()!=0) {
        	model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/completedbyme");
        model.addAttribute("dlm","?");
    	return "homepage";
    }

    @GetMapping("/4meandsubordinates")
    public String getTasks4MeAndSubordinates(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<TasksDto> tasks = tasksService.getTasks4MeAndSubordinates(user.getUser(), pageable);
        if(tasks.getTotalPages()!=0) {
            model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/4meandsubordinates");
        model.addAttribute("dlm","?");
        return "homepage";
    }
    
    @GetMapping("/watching")
    public String getWatchingTasks(@AuthenticationPrincipal UserDetailsImpl user, Model model, @PageableDefault(sort= {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
    	Page<TasksDto> tasks = tasksService.getWatchingTasks(user.getUser(), pageable);
    	if(tasks.getTotalPages()!=0) {
            model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/watching");
        model.addAttribute("dlm","?");
        return "homepage";    	
    }
    
    @GetMapping("/")
    public String getHomePage(@AuthenticationPrincipal UserDetails user, Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        User currentUser = userService.getUserByLogin(user.getUsername());
        Page<TasksDto> tasks = tasksService.getActiveTasksByUser(currentUser,pageable);
        if(tasks.getTotalPages()!=0) {
            model.addAttribute("page",tasks);
        }
        model.addAttribute("url","/");
        model.addAttribute("dlm","?");
        return "homepage";
    }
}
