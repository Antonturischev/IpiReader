package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.Comment;
import ru.turishev.ipireader.model.Markup;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
