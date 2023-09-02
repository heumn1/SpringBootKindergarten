package ru.heumn.SpringBootApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.heumn.SpringBootApp.domain.*;
import ru.heumn.SpringBootApp.repos.*;

@Controller
public class GreetingController {

//    @Autowired
//    private ChildsRepository childsRepository;
    @Autowired
    private CommetsRepo commetsRepo;
//    @GetMapping("/all")
//    public String childsMain(Model model) {
//        Iterable<Childs> childs = childsRepository.findAll();
//        model.addAttribute("childs", childs);
//        return "allChilds";
//    }
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
        return "request";
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
//    @PostMapping("/add")
//    public String childPostADD(
//            @RequestParam String nameChild,@RequestParam String lastname,
//            @RequestParam String patronymic,@RequestParam String age,
//            @RequestParam String groupa,
//            Model model){
//
//        Childs childs = new Childs(nameChild, lastname,patronymic,age,groupa);
//        childsRepository.save(childs);
//        return "redirect:/all";
//    }
}
