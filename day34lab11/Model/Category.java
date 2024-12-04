package com.example.day34lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;



@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "The name should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
