package ru.heumn.SpringBootApp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.heumn.SpringBootApp.domain.Childs;

public interface ChildsRepository extends CrudRepository<Childs, Long> {
}
