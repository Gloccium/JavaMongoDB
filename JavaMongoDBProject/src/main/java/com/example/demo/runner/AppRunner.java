package com.example.demo.runner;

import com.example.demo.entity.Animal;
import com.example.demo.entity.Cat;
import com.example.demo.entity.Dog;
import com.example.demo.entity.Monkey;
import com.example.demo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final AnimalRepository animalRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=== Starting Animal Database Demo ===\n");

        animalRepository.deleteAll();
        System.out.println("✓ Cleared existing data");

        Cat cat1 = new Cat("Whiskers", 3, "Orange", true);
        Cat cat2 = new Cat("Shadow", 5, "Black", false);
        Dog dog1 = new Dog("Rex", 7, "Brown", "German Shepherd");
        Dog dog2 = new Dog("Buddy", 2, "Golden", "Golden Retriever");
        Monkey monkey1 = new Monkey("George", 10, "Brown", "Capuchin", true);
        Monkey monkey2 = new Monkey("Kong", 15, "Black", "Gorilla", false);

        animalRepository.saveAll(List.of(cat1, cat2, dog1, dog2, monkey1, monkey2));
        System.out.println("✓ Added 6 animals to database\n");

        System.out.println("=== Testing CRUD Operations ===\n");

        System.out.println("1. Find all animals:");
        List<Animal> allAnimals = animalRepository.findAll();
        allAnimals.forEach(animal -> System.out.println("   - " + animal.getClass().getSimpleName() + ": " +
                animal.getName() + " (" + animal.getAge() + " years, " +
                animal.getColor() + ") - " + animal.makeSound()));

        System.out.println("\n2. Find by name 'Rex':");
        List<Animal> rexAnimals = animalRepository.findByName("Rex");
        rexAnimals.forEach(animal -> System.out.println("   - Found: " + animal.getName()));

        System.out.println("\n3. Find by color 'Brown':");
        List<Animal> brownAnimals = animalRepository.findByColor("Brown");
        brownAnimals.forEach(animal -> System.out.println("   - " + animal.getName() + " is brown"));

        System.out.println("\n4. Update animal (changing Buddy's age):");
        Animal buddy = animalRepository.findByName("Buddy").get(0);
        buddy.setAge(3);
        animalRepository.save(buddy);
        System.out.println("   - Buddy's new age: " +
                animalRepository.findByName("Buddy").get(0).getAge());

        System.out.println("\n=== Testing Complex @Query Methods ===\n");

        System.out.println("5. Find animals aged 3-10 with Brown color:");
        List<Animal> ageRangeAnimals = animalRepository.findByAgeRangeAndColor(3, 10, "Brown");
        ageRangeAnimals
                .forEach(animal -> System.out.println("   - " + animal.getName() + " (age: " + animal.getAge() + ")"));

        System.out.println("\n6. Find animals older than 5, sorted by age (descending):");
        List<Animal> olderAnimals = animalRepository.findAnimalsOlderThanSortedByAgeDesc(5);
        olderAnimals
                .forEach(animal -> System.out.println("   - " + animal.getName() + " (age: " + animal.getAge() + ")"));

        System.out.println("\n7. Find Dogs with age >= 5:");
        List<Animal> oldDogs = animalRepository.findByTypeAndMinAge(
                "com.example.demo.entity.Dog", 5);
        oldDogs.forEach(animal -> System.out.println("   - " + animal.getName() + " (age: " + animal.getAge() + ")"));

        System.out.println("\n8. Delete animal by name 'Shadow':");
        animalRepository.deleteByName("Shadow");
        long count = animalRepository.count();
        System.out.println("   - Remaining animals: " + count);

        System.out.println("\n=== Demo Completed Successfully ===\n");
    }
}
