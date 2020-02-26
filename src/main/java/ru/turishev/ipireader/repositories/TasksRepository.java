package ru.turishev.ipireader.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.turishev.ipireader.model.DivisionsTopic;
import ru.turishev.ipireader.model.Group;
import ru.turishev.ipireader.model.Task;
import ru.turishev.ipireader.model.User;
import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);

    @Query("from Task t where t.responsible =:user")
    Page<Task> findTasksByResponsibleUser(Pageable pageable, @Param("user") User user);
    
    @Query("from Task t where t.createdBy = :user")
	Page<Task> findAllByCreatedBy(@Param("user")User user, Pageable pageable);
    
    @Query("from Task t where t.responsible =:user and t.status.name='completed'")
	Page<Task> findAllByCompleteByMy(@Param("user")User usr, Pageable pageable);


    @Query("from Task t where t.responsible in (:users) or t.responsibleGroup in (:groups)")
    Page<Task> findTasks4MeAndSubordinates(@Param("users") List<User> users, @Param("groups") List<Group> groups, Pageable pageable);

    @Query(value = "select t.* from tasks_task t " + 
    		"inner join tasks_task_spectators ts on t.id=ts.task_id " + 
    		"inner join user_user u on ts.user_id=u.id " + 
    		"inner join tasks_task_spectator_groups tsg on t.id = tsg.task_id " + 
    		"inner join user_group ug on tsg.group_id=ug.codename " +
    		"where u.id=?1 or ug.codename in ('?2') or t.responsible_id=?1 or t.responsible_group_id in ('?2')", 
    		countQuery = "select count(tt.id) from (select t.* from tasks_task t " + 
    	    		"inner join tasks_task_spectators ts on t.id=ts.task_id " + 
    	    		"inner join user_user u on ts.user_id=u.id " + 
    	    		"inner join tasks_task_spectator_groups tsg on t.id = tsg.task_id " + 
    	    		"inner join user_group ug on tsg.group_id=ug.codename " +
    	    		"where u.id=?1  or ug.codename in ('?2') or t.responsible_id=?1 or t.responsible_group_id in ('?2')" + 
    	    ") tt", 
    		nativeQuery = true)
	Page<Task> findWatchingTasks(Long userid, String groups, Pageable pageable);
    
    @Query(value = "select * from tasks_task tt where tt.id in ( select distinct t.id from tasks_task t " +
                        "inner join user_user u on t.created_by_id=u.id " +
                        "inner join markup_markup m on t.description_id = m.id " +
                        "inner join tasks_comment tc on tc.task_id = t.id "+
                        "inner join markup_markup mm on tc.content_id = mm.id "+
                   "where " +
                        "UPPER(u.fullname) like %?1% " +
                        "and UPPER(t.subject) like %?2% " +
                        "and UPPER(m.text) like %?3% " +
                        "and UPPER(mm.text) like %?4%)",
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

    @Query("from Task  t where t.divisionsTopic in (:topicList)")
    Page<Task> findByTopic(@Param("topicList") List<DivisionsTopic> topicList, Pageable pageable);

}


//
//@Query(value = "select t.* from tasks_task t " + 
//		"	inner join tasks_task_spectators ts on t.id=ts.task_id " + 
//		"	inner join user_user u on ts.user_id=u.id " + 
//		"where u.id=1 " + 
//		"union " + 
//		"select t.* from tasks_task t " + 
//		"	inner join tasks_task_spectator_groups tsg on t.id = tsg.task_id " + 
//		"	inner join user_group ug on tsg.group_id=ug.codename " + 
//		"where ug.codename in ('user_group_aaaa')",
//		countQuery = "select count(tt.id) from (select t.* from tasks_task t " + 
//	    		"	inner join tasks_task_spectators ts on t.id=ts.task_id " + 
//	    		"	inner join user_user u on ts.user_id=u.id " + 
//	    		"where u.id=1 " + 
//	    		"union " + 
//	    		"select t.* from tasks_task t " + 
//	    		"	inner join tasks_task_spectator_groups tsg on t.id = tsg.task_id " + 
//	    		"	inner join user_group ug on tsg.group_id=ug.codename " + 
//	    		"where ug.codename in ('user_group_aaaa')) tt", 
//		nativeQuery = true)