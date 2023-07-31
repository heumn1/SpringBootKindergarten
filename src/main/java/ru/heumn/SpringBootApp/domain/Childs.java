package ru.heumn.SpringBootApp.domain;

import jakarta.persistence.*;

@Entity
public class Childs {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String nameChild;
    private String lastname;
    private String patronymic;
    private String age;
    private String groupa;

    public Childs(String nameChild, String lastname, String patronymic, String age, String groupa) {
        this.nameChild = nameChild;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.age = age;
        this.groupa = groupa;
    }

    public Childs() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameChild() {
        return nameChild;
    }

    public void setNameChild(String nameChild) {
        this.nameChild = nameChild;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGroupa() {
        return groupa;
    }

    public void setGroupa(String groupa) {
        this.groupa = groupa;
    }
}
