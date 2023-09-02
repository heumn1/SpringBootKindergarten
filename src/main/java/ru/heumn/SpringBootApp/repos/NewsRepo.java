package ru.heumn.SpringBootApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.heumn.SpringBootApp.domain.News;

public interface NewsRepo extends JpaRepository<News, Long> {
    News findByTag(String tag);
}
