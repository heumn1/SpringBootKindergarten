package ru.heumn.SpringBootApp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.heumn.SpringBootApp.domain.Comment;

import java.util.List;

public interface CommetsRepo extends CrudRepository<Comment, Long> {
   // List<Comment> findByLastname(String lastname);
}
