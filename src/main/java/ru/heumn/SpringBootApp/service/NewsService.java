package ru.heumn.SpringBootApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.heumn.SpringBootApp.domain.News;
import ru.heumn.SpringBootApp.repos.NewsRepo;

import java.io.File;
import java.io.IOException;
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
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            News news = new News(text,tag,resultFilename);

            newsRepo.save(news);
        }
    }
}
