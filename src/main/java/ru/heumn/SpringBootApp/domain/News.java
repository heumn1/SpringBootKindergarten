package ru.heumn.SpringBootApp.domain;

import jakarta.persistence.*;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=1000000)
    private String text;
    private String tag;

    private String filename;

    public News(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public News(String text, String tag, String filename) {
        this.text = text;
        this.tag = tag;
        this.filename = filename;
    }

    public News() {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
