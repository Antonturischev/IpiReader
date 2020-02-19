package ru.turishev.ipireader.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;
import ru.turishev.ipireader.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TasksDto {
    @Autowired
    UsersRepository usersRepository;
    public Iterable<Task> getActiveTasksByUser(User user) {
        User usr = usersRepository.findById(user.getId()).get();

        List<Task> tasks = usr.getResponsTasks().stream().filter(x->!x.getStatus().getId().equals(3)&&!x.getStatus().getId().equals(4)).collect(Collectors.toList());
        return tasks;
    }
}
