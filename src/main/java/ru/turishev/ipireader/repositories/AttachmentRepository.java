package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}