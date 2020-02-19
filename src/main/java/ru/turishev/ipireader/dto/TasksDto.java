package ru.turishev.ipireader.dto;

import lombok.Builder;
import lombok.Data;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;

@Builder
@Data
public class TasksDto {
	private Task task;
	private Long id;
	private String subject;
	private String description;
	private String status;
	private Project project;
	private String author;
	private Task parent;

	public static TasksDto from(Task task) {
		return TasksDto.builder()
				.id(task.getId())
				.subject(task.getSubject())
				.description(task.getDescription().getText())
				.status(task.getStatus().getName())
				.project(task.getProject())
				.author(task.getCreatedBy().getFullName())
				.parent(task.getParent())
				.build();
	}
}
