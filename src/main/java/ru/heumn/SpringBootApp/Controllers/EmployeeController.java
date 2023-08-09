package ru.heumn.SpringBootApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.heumn.SpringBootApp.domain.Childs;
import ru.heumn.SpringBootApp.domain.Employee;
import ru.heumn.SpringBootApp.domain.News;
import ru.heumn.SpringBootApp.repos.EmployeeRepo;
import ru.heumn.SpringBootApp.service.EmployeeService;

import java.io.IOException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping()
    public String employee(Model model) {
        Iterable<Employee> employees = employeeRepo.findAll();
        model.addAttribute("allEmployees", employees);

        return "employee";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String employeeAdd(
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String patronymic,
            @RequestParam String position,
            @RequestParam MultipartFile file
    ) throws IOException {
        employeeService.addEmployee(name,lastname,patronymic,position,file);
        return "redirect:/employee";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete/")
    public String employeeDelete(
            @RequestParam String name,
            @RequestParam String lastname
    ) throws IOException {
        employeeService.deleteEmployee(name,lastname);
        return "redirect:/employee";
    }

}
