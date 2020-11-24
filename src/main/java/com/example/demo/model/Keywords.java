package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keywords")
public class Keywords {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "keywords")
    private List<Documents> documents = new ArrayList<>();

    public Keywords(){}

    public Keywords(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }
}
