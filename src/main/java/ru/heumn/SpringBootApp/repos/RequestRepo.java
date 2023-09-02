package ru.heumn.SpringBootApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.heumn.SpringBootApp.domain.Request;

public interface RequestRepo extends JpaRepository<Request, Long> {
    Request findByNumber(String number);
}
