package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.repositories.TasksRepository;


@Component
public class TasksService {
	@Autowired
	private TasksRepository tasksRepository;
	
	public Task getById(Long id) {	
		return tasksRepository.findById(id).get();
	}

}
