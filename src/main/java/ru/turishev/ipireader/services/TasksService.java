package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.*;
import ru.turishev.ipireader.repositories.*;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.utils.Utils;

import java.sql.Timestamp;
import java.util.*;


@Service
public class TasksService {

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private MarkupRepository markupRepository;


	public TasksDto getById(Long id) {
		return TasksDto.from(tasksRepository.findById(id));
	}

	public Page<TasksDto> getActiveTasksByUser(User user, Pageable pageable) {
		User usr = usersRepository.findById(user.getId()).get();
		Page<Task> tasks = tasksRepository.findTasksByResponsibleUser(pageable, usr, usr.getGroups());
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getTasksByTopic(DivisionsTopic topic, Pageable pageable) {
		List<DivisionsTopic> topicList = DivisionsTopicService.getChildTopics(topic);
		Page<Task> tasks = tasksRepository.findByTopic(topicList,pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getCreatedByMeTasks(User user, Pageable pageable) {
		User usr = usersRepository.findById(user.getId()).get();
		Page<Task> tasks = tasksRepository.findAllByCreatedBy(usr, pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getCompleteByMeTasks(User user, Pageable pageable) {
		User usr = usersRepository.findById(user.getId()).get();
		Page<Task> tasks = tasksRepository.findAllByCompleteByMy(usr, pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getTasks4MeAndSubordinates(User user, Pageable pageable) {
		User usr = usersRepository.findById(user.getId()).get();
		List<User> users = UserService.getChildSubordinates(usr);
		Set<Group> groups = new HashSet<>();
		for(User u : users){
			groups.addAll(u.getGroups());
		}
		Page<Task> tasks = tasksRepository.findTasks4MeAndSubordinates(users,new ArrayList<>(groups),pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getWatchingTasks(User user, Pageable pageable) {
		User usr = usersRepository.findById(user.getId()).get();
		List<String> grp = new ArrayList<>();
		for(Group group:usr.getGroups()) {
            grp.add(group.getCodename());
		}
		Page<Task> tasks = tasksRepository.findWatchingTasks(usr.getId(),grp,pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public Page<TasksDto> getTasksByProject(Long id, Pageable pageable) {

		Page<Task> tasks = tasksRepository.findAllByProject(id, pageable);
		Page<TasksDto> tasksDto = tasks.map(Utils::convertToTasksDto);
		return tasksDto;
	}

	public void saveTask(Long taskid, Long statusid, String comment, UserDetailsImpl currentUser) {
		Optional<Task> taskFromRepo = tasksRepository.findById(taskid);
		if(taskFromRepo.isPresent()) {
			Task task = taskFromRepo.get();
			Optional<CommonStatus> statusFromRepo = statusRepository.findById(statusid);
			if(statusFromRepo.isPresent()) {
				task.setStatus(statusFromRepo.get());
			}
			if(!comment.equals(" ")) {

				List<Comment> comments = task.getComments();
				comments.add(Comment.builder()
											.author(currentUser.getUser())
											.task(task)
											.dateAdded(new Timestamp(System.currentTimeMillis()))
											.content(Markup.builder().text(comment).metadata("").build())
						.build());
			task.setComments(comments);
			}

			tasksRepository.save(task);
		}
	}
}


