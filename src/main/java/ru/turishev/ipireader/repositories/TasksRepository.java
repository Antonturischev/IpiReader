package ru.turishev.ipireader.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;


import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);

    @Query("from Task t where t.responsible =:user")
    Page<Task> findTasksByResponsibleUser(Pageable pageable, @Param("user") User user);

    @Query(value = "select * from tasks_task t " +
                        "inner join user_user u on t.created_by_id=u.id " +
                        "inner join markup_markup m on t.description_id = m.id " +
                        "inner join tasks_comment tc on tc.task_id = t.id "+
                        "inner join markup_markup mm on tc.content_id = mm.id "+
            "where " +
                        "UPPER(u.fullname) like %?1% " +
                        "and UPPER(t.subject) like %?2% " +
                        "and UPPER(m.text) like %?3% " +
                        "and UPPER(mm.text) like %?4% ",
          countQuery = "select count(distinct(t.id)) from tasks_task t " +
                        "inner join user_user u on t.created_by_id=u.id " +
                        "inner join markup_markup m on t.description_id = m.id " +
                        "inner join tasks_comment tc on tc.task_id = t.id "+
                        "inner join markup_markup mm on tc.content_id = mm.id "+
                   "where " +
                        "UPPER(u.fullname) like %?1% " +
                        "and UPPER(t.subject) like %?2% " +
                        "and UPPER(m.text) like %?3% " +
                        "and UPPER(mm.text) like %?4% ",
          nativeQuery = true)
    Page<Task> findTasksByVarParam(String author, String subject, String description, String comment, Pageable pageable);
}
/*Раюотает плохо

    @Query(value = "select * from tasks_task t where t.subject like %?1% ORDER BY ?#{#pageable}",
          countQuery = "select count(distinct(t.id)) from tasks_task t where t.subject like %?1%",
          nativeQuery = true)
* */