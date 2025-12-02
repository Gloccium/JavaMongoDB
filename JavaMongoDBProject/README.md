# Spring Boot MongoDB Demo Project

## Описание проекта

Мини-проект на Java Spring Boot для работы с MongoDB, демонстрирующий:
- Наследование сущностей (Animal → Cat, Dog, Monkey)
- CRUD операции через MongoRepository
- Сложные запросы с использованием @Query

## Структура проекта

```
homework/
├── docker-compose.yml          # Конфигурация MongoDB в Docker
├── pom.xml                     # Maven зависимости
└── src/
    └── main/
        ├── java/com/example/demo/
        │   ├── DemoApplication.java           # Главный класс приложения
        │   ├── entity/
        │   │   ├── Animal.java                # Абстрактный базовый класс
        │   │   ├── Cat.java                   # Кошка
        │   │   ├── Dog.java                   # Собака
        │   │   └── Monkey.java                # Обезьяна
        │   ├── repository/
        │   │   └── AnimalRepository.java      # Репозиторий с CRUD и @Query
        │   └── runner/
        │       └── AppRunner.java             # Демонстрация функционала
        └── resources/
            └── application.properties         # Конфигурация подключения к БД
```

## Сущности

### Animal (абстрактный класс)
- `id` - уникальный идентификатор
- `name` - имя животного
- `age` - возраст
- `color` - цвет
- `makeSound()` - абстрактный метод для звука

### Cat (Кошка)
- Наследует Animal
- `isIndoor` - домашняя или уличная

### Dog (Собака)
- Наследует Animal
- `breed` - порода

### Monkey (Обезьяна)
- Наследует Animal
- `species` - вид
- `canSwing` - умеет ли качаться на лианах

## Функции репозитория

### Базовые CRUD операции:
- `findAll()` - получить всех животных
- `save()` - сохранить/обновить животное
- `findByName()` - найти по имени
- `findByAge()` - найти по возрасту
- `findByColor()` - найти по цвету
- `deleteByName()` - удалить по имени

### Сложные запросы (@Query):

1. **findByAgeRangeAndColor** - поиск по диапазону возраста и цвету
   ```java
   @Query("{ 'age': { $gte: ?0, $lte: ?1 }, 'color': ?2 }")
   ```

2. **findAnimalsOlderThanSortedByAgeDesc** - поиск животных старше N лет с сортировкой
   ```java
   @Query(value = "{ 'age': { $gt: ?0 } }", sort = "{ 'age': -1 }")
   ```

3. **findByTypeAndMinAge** - поиск по типу животного и минимальному возрасту
   ```java
   @Query("{ '_class': ?0, 'age': { $gte: ?1 } }")
   ```

## Запуск проекта

### 1. Запустить MongoDB в Docker

```bash
docker-compose up -d
```

Или если контейнер уже существует:
```bash
docker start mongodb_container
```

### 2. Запустить Spring Boot приложение

**Через Maven (если установлен):**
```bash
mvn spring-boot:run
```

**Через IDE (IntelliJ IDEA, Eclipse):**
1. Открыть проект как Maven проект
2. Запустить `DemoApplication.java`

**Через Maven Wrapper (если есть):**
```bash
./mvnw spring-boot:run
```

### 3. Проверка работы

При запуске приложения автоматически выполнится `AppRunner`, который:
- Очистит базу данных
- Добавит 6 животных (2 кошки, 2 собаки, 2 обезьяны)
- Продемонстрирует все CRUD операции
- Выполнит сложные запросы
- Выведет результаты в консоль

## Подключение к MongoDB

Параметры подключения (в `application.properties`):
- **Host:** localhost
- **Port:** 27017
- **Database:** animals_db
- **Username:** admin
- **Password:** password

## Проверка MongoDB

Подключиться к MongoDB через Docker:
```bash
docker exec -it mongodb_container mongosh -u admin -p password
```

Посмотреть данные:
```javascript
use animals_db
db.animals.find().pretty()
```

## Требования

- Java 23
- Docker
- Maven (опционально, если используется IDE)

## Примеры вывода

При успешном запуске вы увидите:
```
=== Starting Animal Database Demo ===
✓ Cleared existing data
✓ Added 6 animals to database

=== Testing CRUD Operations ===
1. Find all animals:
   - Cat: Whiskers (3 years, Orange) - Meow!
   - Dog: Rex (7 years, Brown) - Woof!
   ...

=== Testing Complex @Query Methods ===
5. Find animals aged 3-10 with Brown color:
   - Rex (age: 7)
   ...

=== Demo Completed Successfully ===
```
