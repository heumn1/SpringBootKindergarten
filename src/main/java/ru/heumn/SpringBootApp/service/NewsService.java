package ru.heumn.SpringBootApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.heumn.SpringBootApp.domain.News;
import ru.heumn.SpringBootApp.repos.NewsRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class NewsService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private NewsRepo newsRepo;

    public void addNews(String tag, String text, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //Создание индивидуального названия для каждогой файла
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            //Загрузка файла на жесткий диск
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            News news = new News(text,tag,resultFilename);

            //добавление данных в БД
            newsRepo.save(news);
        }
    }

    public void deleteNews(String name) {
        News news = newsRepo.findByTag(name);

        String filename = news.getFilename();
        //получение ссылки нахождения файла
        Path path = Paths.get(uploadPath + "\\" + filename);

        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //удаление записи из БД
        newsRepo.delete(news);
    }
}
