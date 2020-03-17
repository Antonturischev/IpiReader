package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.CommonStatus;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<CommonStatus, Long> {
    Optional<CommonStatus> findById(Long id);
}
