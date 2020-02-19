
package ru.turishev.ipireader.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.TasksRepository;

@Controller
public class TaskController {
	@Autowired
	TasksRepository tasksRepository;
	
	@GetMapping("/task/{id}")
	public String getTaskById(@PathVariable Long id,Model model) {
		Optional<Task> result = tasksRepository.findById(id);
		if(result.isPresent()) {
			model.addAttribute("task",result.get());
		}
		
		return "task";
	}
}
