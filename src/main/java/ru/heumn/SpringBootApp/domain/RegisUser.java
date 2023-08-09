package ru.heumn.SpringBootApp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reguser")
public class RegisUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле username не может быть пустым")
    @Size(min = 2, max = 20, message = "логин не должен содержать больше 20 символов или быть меньше 2 символов")
    private String username;
    @NotEmpty(message = "Поле password не может быть пустым")
    private String password;
    private String activationCode;
    @NotEmpty(message = "Поле email не может быть пустым")
    @Email(message = "Неверное значение в поле email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
