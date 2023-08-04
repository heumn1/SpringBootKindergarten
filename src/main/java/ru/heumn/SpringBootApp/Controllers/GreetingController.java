package ru.heumn.SpringBootApp.Controllers;

import jakarta.persistence.CollectionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.heumn.SpringBootApp.domain.*;
import ru.heumn.SpringBootApp.repos.ChildsRepository;
import ru.heumn.SpringBootApp.repos.CommetsRepo;
import ru.heumn.SpringBootApp.repos.RegisUserRepo;
import ru.heumn.SpringBootApp.repos.UserRepo;
import ru.heumn.SpringBootApp.service.UserService;

import java.net.PortUnreachableException;
import java.util.Collections;

@Controller
public class GreetingController {
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
    public String registration(Model model) {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(RegisUser regisUser, Model model) {
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

//    Данный PostMapping использовать без подтверждения аккаунта

//    @PostMapping("/registration")
//    public String addUser(User user, Model model) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//        if(userFromDb != null)
//        {
//             model.addAttribute("message", "User exists");
//             return "registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepo.save(user);
//
//
//        return "redirect:/login";
//    }


    @GetMapping("/all")
    public String childsMain(Model model) {
        Iterable<Childs> childs = childsRepository.findAll();
        model.addAttribute("childs", childs);
        return "allChilds";
    }
    @GetMapping("/comment")
    public String comment(Model model) {
        Iterable<Comment> comments = commetsRepo.findAll();
        model.addAttribute("comments", comments);
        return "comment";
    }
    @PostMapping("/comment")
    public String commentAdd(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Model model){
        Comment comment = new Comment(text,user);

        commetsRepo.save(comment);
        return "about";
    }

    @GetMapping("/addChild")
    public String addChild(Model model) {
        return "addChild";
    }
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
    @GetMapping("/")
    public String mainChild(Model model) {
        return "index";
    }

//    @PostMapping("/filter")
//    public String childfind(@RequestParam String lastname, Model model){
//        Iterable<Childs> childs = childsRepository.findByLastname(lastname);
//        model.addAttribute("childs", childs);
//        return "allChilds";
//    }
    @PostMapping("/add")
    public String childPostADD(
            @RequestParam String nameChild,@RequestParam String lastname,
            @RequestParam String patronymic,@RequestParam String age,
            @RequestParam String groupa,
            Model model){

        Childs childs = new Childs(nameChild, lastname,patronymic,age,groupa);
        childsRepository.save(childs);
        return "redirect:/all";
    }
}
