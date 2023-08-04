package ru.heumn.SpringBootApp.domain;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Comment() {
    }

    public Comment(String text, User user) {
        this.author = user;
        this.text = text;
    }

    public String getAuthorName()
    {
        return author.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
