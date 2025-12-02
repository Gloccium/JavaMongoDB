package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "animals")
public class Monkey extends Animal {
    private String species;
    private Boolean canSwing;

    public Monkey() {
        super();
    }

    public Monkey(String name, Integer age, String color, String species, Boolean canSwing) {
        super(name, age, color);
        this.species = species;
        this.canSwing = canSwing;
    }

    @Override
    public String makeSound() {
        return "Ooh ooh ah ah!";
    }
}
