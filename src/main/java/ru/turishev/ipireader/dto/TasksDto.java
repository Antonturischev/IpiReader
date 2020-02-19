package ru.turishev.ipireader.dto;


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
}
