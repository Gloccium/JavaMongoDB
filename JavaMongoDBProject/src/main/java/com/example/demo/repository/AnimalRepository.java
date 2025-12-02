package com.example.demo.repository;

import com.example.demo.entity.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    List<Animal> findByName(String name);

    List<Animal> findByAge(Integer age);

    List<Animal> findByColor(String color);

    void deleteByName(String name);

    @Query("{ 'age': { $gte: ?0, $lte: ?1 }, 'color': ?2 }")
    List<Animal> findByAgeRangeAndColor(Integer minAge, Integer maxAge, String color);

    @Query(value = "{ 'age': { $gt: ?0 } }", sort = "{ 'age': -1 }")
    List<Animal> findAnimalsOlderThanSortedByAgeDesc(Integer age);

    @Query("{ '_class': ?0, 'age': { $gte: ?1 } }")
    List<Animal> findByTypeAndMinAge(String className, Integer minAge);
}
