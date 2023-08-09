package ru.heumn.SpringBootApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.heumn.SpringBootApp.domain.News;
import ru.heumn.SpringBootApp.repos.NewsRepo;
import ru.heumn.SpringBootApp.service.NewsService;

import java.io.IOException;

@Controller
public class NewsController {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private NewsService newsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/news/delete/{tag}")
    public String DeleteNews(@PathVariable String tag){
        newsService.deleteNews(tag);
        return "redirect:/news";
    }

    @GetMapping("/news")
    public String news(Model model) {
        Iterable<News> news = newsRepo.findAll();
        model.addAttribute("allNews", news);

        return "news";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/news/addNews")
    public String addNews( @RequestParam String text,
                           @RequestParam String tag,
                           @RequestParam MultipartFile file
    ) throws IOException {
        newsService.addNews(tag,text,file);
        return "redirect:/news";
    }
}
