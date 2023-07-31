package ru.heumn.SpringBootApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.heumn.SpringBootApp.domain.Childs;
import ru.heumn.SpringBootApp.repos.ChildsRepository;

@Controller
public class GreetingController {
    @Autowired
    private ChildsRepository childsRepository;

    @GetMapping("/all")
    public String childsMain(Model model) {
        Iterable<Childs> childs = childsRepository.findAll();
        model.addAttribute("childs", childs);
        return "allChilds";
    }
    @PostMapping("/filter")
    public String childfind(@RequestParam String lastname, Model model){
        Iterable<Childs> childs = childsRepository.findByLastname(lastname);
        model.addAttribute("childs", childs);
        return "allChilds";
    }
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
