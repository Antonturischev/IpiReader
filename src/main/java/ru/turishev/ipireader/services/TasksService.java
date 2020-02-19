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
   	@Autowired
   	UsersRepository usersRepository;
	
	public Task getById(Long id) {	
		return tasksRepository.findById(id).get();
	}

    public Iterable<Task> getActiveTasksByUser(User user) {
        User usr = usersRepository.findById(user.getId()).get();

        List<Task> tasks = usr.getResponsTasks().stream().filter(x->!x.getStatus().getId().equals(3)&&!x.getStatus().getId().equals(4)).collect(Collectors.toList());
        return tasks;
    }

}
