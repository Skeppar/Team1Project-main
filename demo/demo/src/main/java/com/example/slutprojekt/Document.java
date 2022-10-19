package com.example.slutprojekt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Document {
    @Id // Primärnyckeln
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;
    private String fileName; // = "/files/default.jpg";

    public Document() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

