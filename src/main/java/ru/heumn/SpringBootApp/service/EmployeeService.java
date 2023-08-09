package ru.heumn.SpringBootApp.service;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.heumn.SpringBootApp.domain.Employee;
import ru.heumn.SpringBootApp.domain.News;
import ru.heumn.SpringBootApp.repos.EmployeeRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class EmployeeService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private EmployeeRepo employeeRepo;

    public void addEmployee(String name, String lastName, String patronymic, String position, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //Создание индивидуального названия для каждогой файла
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            //Загрузка файла на жесткий диск
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            Employee employee = new Employee(name,lastName,patronymic,position,resultFilename);

            //добавление данных в БД
            employeeRepo.save(employee);
        }
    }

    public void deleteEmployee(String name, String lastName){

        Employee employee = employeeRepo.findByNameAndLastname(name,lastName);

        String filename = employee.getFilename();
        //получение ссылки нахождения файла
        Path path = Paths.get(uploadPath + "\\" + filename);

        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //удаление записи из БД
        employeeRepo.delete(employee);
    }
}
