package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "animals")
public abstract class Animal {
    @Id
    private String id;

    private String name;
    private Integer age;
    private String color;

    public Animal() {
    }

    public Animal(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public abstract String makeSound();
}
