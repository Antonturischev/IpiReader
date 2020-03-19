
package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.services.TasksService;

import java.util.Arrays;

@Controller
public class TaskController {

	@Autowired 
	TasksService tasksService;
	
	@GetMapping("/task/{id}")
	public String getTaskById(@PathVariable Long id, Model model) {
		TasksDto task = tasksService.getById(id);
		model.addAttribute("task",task);
		return "task";
	}
	@PostMapping("/task")
	public String searchTaskById(@RequestParam String id) {
		return "redirect:/task/"+id;
	}

	@PostMapping("/task/{id}")
	public String saveTask(@PathVariable Long id,
						   @RequestParam Long statusid,
						   @RequestParam String comment,
						   @RequestParam MultipartFile[] file,
						   @AuthenticationPrincipal UserDetailsImpl currentUser) {
		tasksService.saveTask(id, statusid, comment, currentUser, Arrays.asList(file));
		return "redirect:/task/"+id;
	}
}
