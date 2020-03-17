package ru.turishev.ipireader.dto;

import lombok.Builder;
import lombok.Data;
import ru.turishev.ipireader.model.Attachment;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.model.Project;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.services.DivisionsTopicService;
import ru.turishev.ipireader.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Data
public class TasksDto {
	private static final String path="http://ipi-manager3/";
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
	private List<CommentDto> comments;
	private List<String> spectrators;
	private List<String> spectratorsGroup;
	private List<String> usersFromTopic;
	private List<String> groupFromTopic;
	private List<Attachment> attachments;
	private String attachmentsPath;

	public static TasksDto from(Optional<Task> taskOptional) {
		if(taskOptional.isPresent()) {
			Task task = taskOptional.get();
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
					.comments(task.getComments().stream().map(x->CommentDto.from(x)).collect(Collectors.toList()))
					.spectrators(task.getSpectrators().stream().map(x->x.getFullName()).collect(Collectors.toList()))
					.spectratorsGroup(task.getSpectratorsGroup().stream().map(x->x.getName()).collect(Collectors.toList()))
					.usersFromTopic(task.getDivisionsTopic().getTopicAccessGrant().stream().map(
							x->{
								if (x.getUser()!=null&&(x.is_manager()||x.is_spectator())) return x.getUser().getFullName();
								else return null;
								}).filter(x->x!=null).collect(Collectors.toList()))
					.groupFromTopic(task.getDivisionsTopic().getTopicAccessGrant().stream().map(
							x->{
								if (x.getGroup()!=null&&(x.is_manager()||x.is_spectator())) return x.getGroup().getName();
								else return null;
								}).filter(x->x!=null).collect(Collectors.toList()))
					.attachments(task.getAttachments())
					.attachmentsPath(TasksDto.getPathByNumber(task.getId()))
					.build();
		}
	return null;		
	}
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
					.comments(task.getComments().stream().map(x->CommentDto.from(x)).collect(Collectors.toList()))
					.spectrators(task.getSpectrators().stream().map(x->x.getFullName()).collect(Collectors.toList()))
					.spectratorsGroup(task.getSpectratorsGroup().stream().map(x->x.getName()).collect(Collectors.toList()))
					.usersFromTopic(task.getDivisionsTopic().getTopicAccessGrant().stream().map(
							x->{
								if (x.getUser()!=null&&(x.is_manager()||x.is_spectator())) return x.getUser().getFullName();
								else return null;
								}).filter(x->x!=null).collect(Collectors.toList()))
					.groupFromTopic(task.getDivisionsTopic().getTopicAccessGrant().stream().map(
							x->{
								if (x.getGroup()!=null&&(x.is_manager()||x.is_spectator())) return x.getGroup().getName();
								else return null;
								}).filter(x->x!=null).collect(Collectors.toList()))
					.attachments(task.getAttachments())
					.attachmentsPath(TasksDto.getPathByNumber(task.getId()))
					.build();	
		}

	private static String getPathByNumber(Long id) {
		Integer pathPart = (id%10000!=0)?(int)Math.floor(id/10000):(int)Math.floor(id/10000-1);
		if(pathPart<1) {
			return path+"1-10000/"+String.valueOf(id)+"/";
		}
		else {
			return path + pathPart.toString() + "0001-" + (++pathPart).toString() + "0000/" + String.valueOf(id) + "/";
		}
	}
}
