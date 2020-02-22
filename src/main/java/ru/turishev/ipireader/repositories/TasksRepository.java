package ru.turishev.ipireader.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;


import java.util.Optional;

public interface TasksRepository extends JpaRepository<Task,Long> {
    Optional<Task> findById(Long id);

    @Query("from Task t where t.responsible =:user")
    Page<Task> findTasksByResponsibleUser(Pageable pageable, @Param("user") User user);
//    @Query("from  Task t where t.:paramName =:paramValue")
//    Page<Task> findTasksByVarParam(Pageable pageable,String paramName, String paramValue);
}
