package ru.heumn.SpringBootApp.repos;

import org.springframework.data.repository.CrudRepository;
import ru.heumn.SpringBootApp.domain.Childs;

import java.util.List;

public interface ChildsRepository extends CrudRepository<Childs, Long> {

    List<Childs> findByLastname(String lastname);

}
