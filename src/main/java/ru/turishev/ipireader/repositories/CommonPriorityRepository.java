package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.CommonPriority;

import java.util.Optional;

public interface CommonPriorityRepository extends JpaRepository<CommonPriority,Long> {
    Optional<CommonPriority> findById(Long id);
}
