package ru.heumn.SpringBootApp.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.heumn.SpringBootApp.domain.RegisUser;
import ru.heumn.SpringBootApp.domain.Request;
import ru.heumn.SpringBootApp.repos.RequestRepo;
import ru.heumn.SpringBootApp.service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    RequestService requestService;
    @Autowired
    RequestRepo requestRepo;

    @GetMapping("/")
    public String addRequest(
            @ModelAttribute("request") Request request,
            Model model
            ){
        return "request";
    }
    @GetMapping("/check")
    public String checkRequest(
            Model model
    ){
        Iterable<Request> requests = requestRepo.findAll();
        model.addAttribute("requests", requests);
        return "check";
    }
    @PostMapping("/")
    public String addRequest(
            @ModelAttribute("request") @Valid Request request, BindingResult bindingResult, Model model
    ){
        if(bindingResult.hasErrors()){
            return "request";
        }
        if(requestService.requestIsExists(request)){
            model.addAttribute("errorRequest", "error");
        }
        else {
            requestService.addRequest(request);
        }

        return "request";
    }

    @GetMapping("/delete/{number}")
    public String deleteRequest(@PathVariable String number) {
        requestService.delete(number);
        return "redirect:/request/check";
    }
}
