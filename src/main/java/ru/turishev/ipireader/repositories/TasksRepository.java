package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.Task;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Task,Long> {
    Optional<Task> findById(Long id);
}
