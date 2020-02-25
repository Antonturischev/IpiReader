
package ru.turishev.ipireader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.services.TasksService;

@Controller
public class TaskController {

	@Autowired 
	TasksService tasksService;
	
	@GetMapping("/task/{id}")
	public String getTaskById(@PathVariable Long id,Model model) {
		TasksDto task = tasksService.getById(id);
		model.addAttribute("task",task);
		return "task";
	}
	@PostMapping("/task")
	public String searchTaskById(@RequestParam String id) {
		return "redirect:/task/"+id;
	}
}
