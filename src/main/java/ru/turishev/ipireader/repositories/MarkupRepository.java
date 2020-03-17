package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turishev.ipireader.model.Markup;

public interface MarkupRepository extends JpaRepository<Markup, Long> {
}
