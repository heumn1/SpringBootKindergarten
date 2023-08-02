package ru.heumn.SpringBootApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.heumn.SpringBootApp.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
