package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.turishev.ipireader.model.DivisionsTopic;

import java.util.List;
import java.util.Optional;

public interface DivisionsTopicRepository extends JpaRepository<DivisionsTopic,Long> {
    Optional<DivisionsTopic> findById(Long id);
    
    @Query(value = "select dt.* from divisions_topic dt where dt.mptt_level=?1 and dt.archived='false'", nativeQuery = true)
    List<DivisionsTopic> findAllByMpttLevel(Integer level);
}
