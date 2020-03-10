package ru.turishev.ipireader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.turishev.ipireader.model.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    
    @Query("From User u where u.login = :login and u.dateRemoved is null")
    Optional<User> findOneByLogin(@Param("login")String login);
}
