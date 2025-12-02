package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "animals")
public class Dog extends Animal {
    private String breed;

    public Dog() {
        super();
    }

    public Dog(String name, Integer age, String color, String breed) {
        super(name, age, color);
        this.breed = breed;
    }

    @Override
    public String makeSound() {
        return "Woof!";
    }
}
