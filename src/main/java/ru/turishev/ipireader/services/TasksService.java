package ru.turishev.ipireader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.turishev.ipireader.dto.TasksDto;
import ru.turishev.ipireader.model.*;
import ru.turishev.ipireader.repositories.*;
import ru.turishev.ipireader.security.UserDetailsImpl;
import ru.turishev.ipireader.utils.FileUtils;
import ru.turishev.ipireader.utils.Utils;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TasksService {

	@Autowired
	private TasksRepository tasksRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private DivisionsTopicRepository divisionsTopicRepository;
	@Autowired
	private CommonPriorityRepository commonPriorityRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private MailSender mailSender;


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

	public Set<User> getAllTaskSpectators(Task task) {
		Set<User> spectators = new HashSet<>();
		spectators.add(task.getAuthor()); //автор
		spectators.add(task.getResponsible()); //ответственный
		spectators.addAll(task.getSpectrators()); //наблюдатели из заявки
		task.getSpectratorsGroup().forEach(group->spectators.addAll(group.getUsers())); //группы наблюдателей из заявки
		spectators.addAll(task.getResponsibleGroup().getUsers()); //ответственная группа
		spectators.addAll(task.getDivisionsTopic().getTopicAccessGrant().stream().map(
				x->{
					if (x.getUser()!=null&&(x.is_manager()||x.is_spectator())) return x.getUser();
					else return null;
				}).filter(x->x!=null).collect(Collectors.toList()));

		return spectators;
	}

	public void saveTask(Long taskid, Long statusid, String comment, UserDetailsImpl currentUser, List<MultipartFile> files) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        User user = currentUser.getUser();
	    Optional<Task> taskFromRepo = tasksRepository.findById(taskid);
		if(taskFromRepo.isPresent()) {
			Task task = taskFromRepo.get();
			Optional<CommonStatus> statusFromRepo = statusRepository.findById(statusid);
			if(statusFromRepo.isPresent()) {
				task.setStatus(statusFromRepo.get());
				if (statusFromRepo.get().getId()==7||statusFromRepo.get().getId()==5) {
					task.setResponsible(currentUser.getUser());
					task.setResponsibleGroup(null);
					task.setDateChanged(new Timestamp(System.currentTimeMillis()));
					task.setDateClosed(new Timestamp(System.currentTimeMillis()));
				}
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
			task.setDateChanged(new Timestamp(System.currentTimeMillis()));
			}
			tasksRepository.save(task);
            if(files!=null){
                for(MultipartFile file:files) {
                    if(file!=null&&!file.getOriginalFilename().isEmpty()){
                        try {
                            fileUtils.saveFile(task.getId(), file);
                            Attachment ath = Attachment.builder().task(task).dateAdded(time).filename(file.getOriginalFilename()).original_filename(file.getOriginalFilename()).author(user).build();
                            attachmentRepository.save(ath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
			/*Добавить тут оповещение по email*/
			mailSender.send(getAllTaskSpectators(task),"Тест","Заявка изменена");
		}
	}

	public Long createTask(Long topicid, String subject, String description, List<MultipartFile> files, UserDetailsImpl currentUser) {
		if(subject.equals("")) return -1L;
		User user = currentUser.getUser();
		DivisionsTopic topic = divisionsTopicRepository.findById(topicid).orElseThrow(IllegalArgumentException::new);
		CommonPriority priority = commonPriorityRepository.findById(4L).orElseThrow(IllegalArgumentException::new);
		CommonStatus status = statusRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Task task = Task.builder()
				.subject(subject)
				.description(Markup.builder().text(description).metadata("").build())
				.createdBy(user)
				.author(user)
				.dateAdded(time)
				.dateChanged(time)
				.divisionsTopic(topic)
				.duration(0L)
				.brokenReactionLevel(0L)
				.severity(4L)
				.anonymousReporter("")
				.statusChangeReason("")
				.closeIfAllChildClosed(false)
				.markedExpired(false)
				.priority(priority)
				.status(status)
			//	.attachments(files.stream().map((x)->Attachment.builder().filename(x.getOriginalFilename()).author(user).dateAdded(time).build()).collect(Collectors.toList()))
				.responsibleGroup(topic.getResponsibleGroup())
		.build();
		task=tasksRepository.save(task);
		if(files!=null){
			for(MultipartFile file:files) {
				if(file!=null&&!file.getOriginalFilename().isEmpty()){
					try {
						fileUtils.saveFile(task.getId(), file);
						Attachment ath = Attachment.builder().task(task).dateAdded(time).filename(file.getOriginalFilename()).original_filename(file.getOriginalFilename()).author(user).build();
						attachmentRepository.save(ath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return task.getId();
		/*Добавить тут оповещение по email*/
	}
}


