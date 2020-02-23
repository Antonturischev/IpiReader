package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.repositories.TasksRepository;
import ru.turishev.ipireader.repositories.UsersRepository;
import ru.turishev.ipireader.utils.Utils;


@Service
public class TasksService {
	@Autowired
	private TasksRepository tasksRepository;
   	@Autowired
	UsersRepository usersRepository;
	
	public TasksDto getById(Long id) {
		return TasksDto.from(tasksRepository.findById(id));
	}

    public Page<TasksDto> getActiveTasksByUser(User user, Pageable pageable) {
        User usr = usersRepository.findById(user.getId()).get();
		Page<Task> tasks = tasksRepository.findTasksByResponsibleUser(pageable, usr);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
        return tasksDto;
    }
//    public TasksDto convertToTasksDto(Task task) {
//		return TasksDto.from(task);
//	}

}
