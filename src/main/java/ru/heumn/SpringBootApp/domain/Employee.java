package ru.heumn.SpringBootApp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 20, message = "Имя не должено содержать больше 20 символов или быть меньше 2 символов")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 20, message = "Фамилия не должена содержать больше 20 символов или быть меньше 2 символов")
    private String lastname;
    private String patronymic;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 25, message = "Должность не должена содержать больше 25 символов или быть меньше 2 символов")
    private String position;

    private String filename;

    public Employee() {
    }

    public Employee(String name, String lastname, String patronymic, String position, String filename) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.position = position;
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
