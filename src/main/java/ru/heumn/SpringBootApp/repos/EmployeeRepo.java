package ru.heumn.SpringBootApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.heumn.SpringBootApp.domain.Employee;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByNameAndLastname(String name, String lastname);
    Employee findByName(String name);
}
