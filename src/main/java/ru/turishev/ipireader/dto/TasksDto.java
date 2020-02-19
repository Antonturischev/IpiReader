package ru.turishev.ipireader.dto;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TasksDto {

=======

import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.services.TasksService;

@Builder
@AllArgsConstructor
public class TasksDto {
	private Task task;
	private Long id;
	private String subject;
	private String description;
	private String status;
	private Project project;
	private String author;
	private Task parent;

	public TasksDto(Long id) {
		this.id=id;
	}
	@Autowired
	private void setTask(TasksService tasksService) {
		this.task=tasksService.getById(id);
	}
	
	public Long getId() {
		return task.getId();
	}
	public String getSubject() {
		return task.getSubject();
	}
	public String getDescription() {
		return task.getDescription().getText();
	}
	public String getStatus() {
		return task.getStatus().getName();
	}
	public Project getProject() {
		return project;
	}
	public String getAuthor() {
		return task.getCreatedBy().getFullName();
	}
	public Task getParent() {
		return task.getParent();
	}	
>>>>>>> 05036ae8169dda264c5576b0922de59fe6df152a
}
