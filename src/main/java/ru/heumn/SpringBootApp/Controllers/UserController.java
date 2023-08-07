package ru.heumn.SpringBootApp.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.heumn.SpringBootApp.domain.RegisUser;
import ru.heumn.SpringBootApp.repos.ChildsRepository;
import ru.heumn.SpringBootApp.repos.CommetsRepo;
import ru.heumn.SpringBootApp.repos.RegisUserRepo;
import ru.heumn.SpringBootApp.repos.UserRepo;
import ru.heumn.SpringBootApp.service.UserService;

@Controller
public class UserController {
    @Autowired
    private ChildsRepository childsRepository;
    @Autowired
    private CommetsRepo commetsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RegisUserRepo regisUserRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("regisUser") RegisUser regisUser) {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("regisUser") @Valid RegisUser regisUser, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "registration";
        }

        if(!userService.addUser(regisUser))
        {
            model.addAttribute("message", "User exists");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        if(isActivated) {
            System.out.printf("activate");
        }

        return "login";
    }

}
