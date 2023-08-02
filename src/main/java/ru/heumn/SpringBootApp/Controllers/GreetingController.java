package ru.heumn.SpringBootApp.Controllers;

import jakarta.persistence.CollectionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.heumn.SpringBootApp.domain.Childs;
import ru.heumn.SpringBootApp.domain.Role;
import ru.heumn.SpringBootApp.domain.User;
import ru.heumn.SpringBootApp.repos.ChildsRepository;
import ru.heumn.SpringBootApp.repos.UserRepo;

import java.net.PortUnreachableException;
import java.util.Collections;

@Controller
public class GreetingController {
    @Autowired
    private ChildsRepository childsRepository;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
             model.addAttribute("message", "User exists");
             return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);


        return "redirect:/login";
    }


    @GetMapping("/all")
    public String childsMain(Model model) {
        Iterable<Childs> childs = childsRepository.findAll();
        model.addAttribute("childs", childs);
        return "allChilds";
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
