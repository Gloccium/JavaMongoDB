package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "animals")
public class Cat extends Animal {
    private Boolean isIndoor;

    public Cat() {
        super();
    }

    public Cat(String name, Integer age, String color, Boolean isIndoor) {
        super(name, age, color);
        this.isIndoor = isIndoor;
    }

    @Override
    public String makeSound() {
        return "Meow!";
    }
}
