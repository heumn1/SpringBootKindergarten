package ru.heumn.SpringBootApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.heumn.SpringBootApp.domain.RegisUser;

public interface RegisUserRepo extends JpaRepository<RegisUser, Long> {
    RegisUser findByUsername(String username);

    RegisUser findByActivationCode(String code);
}
