# University Management System (Java OOP)

A console-based Java project that models a small university: departments, courses, teachers and students. Built as a course project to practice object-oriented programming concepts.

## Features

- Register students into courses with a capacity limit
- Custom exception (`CourseFullException`) when a course is full
- Sort students by GPA for a dean's honor roll
- Payroll for teachers and monthly stipends for students through a shared `Payable` interface
- Performance bonus that applies only to professors
- Courses inside a department stay sorted by course code

## OOP concepts used

| Concept | Where |
|---|---|
| Abstract class & inheritance | `Person` → `Student`, `Teacher` |
| Interfaces | `Payable`, `Registerable`, `Comparable<Student>` |
| Polymorphism | Processing payments through a `List<Person>` |
| Custom exceptions | `CourseFullException` |
| Enum | `Rank` (Lecturer, Assistant Prof, Professor) |
| Inner class | `Course.Schedule` |
| Generics | `Department.printList(List<T>, String)` |
| Collections | `ArrayList`, `Vector`, `TreeSet` with a `Comparator` |
| Static members | Total people counter in `Person` |

## Project structure

```
src/
├── app/
│   └── UniversitySystem.java      # main program
├── model/
│   ├── Person.java
│   ├── Student.java
│   ├── Teacher.java
│   ├── Course.java
│   └── Department.java
├── interfaces/
│   ├── Payable.java
│   └── Registerable.java
└── exceptions/
    └── CourseFullException.java
```

## How to run

Requires JDK 8 or newer.

```bash
javac -d out $(find src -name "*.java")
java -cp out app.UniversitySystem
```

## Sample output

```
[Registration] Processing enrollment requests...
Success: Khalid Al-Mutairi has been added to CS101
Success: Omar Al-Harbi has been added to CS101
Attempting to register Fahad Al-Dossari...
>> CRITICAL ALERT: Registration failed for Fahad Al-Dossari: Course CS101 is full.

--- Dean's Honor Roll (Sorted by GPA) ---
   -> ID: 103 | Name: Omar Al-Harbi | ... | GPA: 3.9
   -> ID: 101 | Name: Khalid Al-Mutairi | ... | GPA: 3.8
   -> ID: 102 | Name: Fahad Al-Dossari | ... | GPA: 2.5
```
