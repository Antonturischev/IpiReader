package ru.turishev.ipireader.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;
import ru.turishev.ipireader.model.Attachment;
import ru.turishev.ipireader.model.Comment;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.model.Group;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.services.DivisionsTopicService;
import ru.turishev.ipireader.utils.Utils;

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
	private List<Task> children;
	private LinkedList<DivisionsTopic> topics;
	private String responsible;
	private String responsibleGroup;
	private String priority;
	private String dateAdded;
	private String dateChanged;
	private String dateClosed;
	private List<Comment> commenst;
	private List<String> spectrators;
	private List<String> spectratorsGroup;
	private List<Attachment> attachments;

	public static TasksDto from(Task task) {
		return TasksDto.builder()
				.id(task.getId())
				.subject(task.getSubject())
				.description((task.getDescription()!=null)?task.getDescription().getText():null)
				.status((task.getStatus()!=null)?task.getStatus().getName():null)
				.project(task.getProject())
				.author((task.getCreatedBy()!=null)?task.getCreatedBy().getFullName():null)
				.parent(task.getParent())
				.children(task.getChildren())
				.topics(DivisionsTopicService.getParentTopics(task.getDivisionsTopic()))
				.responsible((task.getResponsible()!=null)?task.getResponsible().getFullName():null)
				.responsibleGroup((task.getResponsibleGroup()!=null)?task.getResponsibleGroup().getName():null)
				.priority((task.getPriority()!=null)?task.getPriority().getCodename():null)
				.dateAdded(Utils.convertTimestampToString(task.getDateAdded()))
				.dateChanged((task.getDateChanged()!=null)?Utils.convertTimestampToString(task.getDateChanged()):null)
				.dateClosed((task.getDateClosed()!=null)?Utils.convertTimestampToString(task.getDateClosed()):null)
				.commenst(task.getComments())
				.spectrators(task.getSpectrators().stream().map(x->x.getFullName()).collect(Collectors.toList()))
				.spectratorsGroup(task.getSpectratorsGroup().stream().map(x->x.getName()).collect(Collectors.toList()))
				.build();
	}
}
