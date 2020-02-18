package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.DivisionsTopic;

import java.util.Optional;

public interface DivisionsTopicRepository extends JpaRepository<DivisionsTopic,Long> {
    Optional<DivisionsTopic> findById(Long id);
}
