package ru.heumn.SpringBootApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.heumn.SpringBootApp.domain.Comment;
import ru.heumn.SpringBootApp.repos.CommetsRepo;
import ru.heumn.SpringBootApp.repos.UserRepo;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "adminPanel";
    }

    @GetMapping("/check")
    public String checkChildAdd(Model model) {
        return "check";
    }

}
