package app;

import model.*;
import exceptions.CourseFullException;
import interfaces.Payable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main application class.
 * This simulates a day in the life of the university system administrator.
 */
public class UniversitySystem {
    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   UNIVERSITY MANAGEMENT SYSTEM v1.0");
        System.out.println("==========================================\n");

        // ---------------------------------------------------------
        // Step 1: Initialize the Department and Resources
        // ---------------------------------------------------------
        System.out.println("[Init] Setting up departments and courses...");
        
        // Updated Department Head to an Arabic name
        Department csDept = new Department("Computer Science", "Prof. Abdullah Al-Ghamdi");

        // Creating courses using the inner class logic for schedules
        Course javaCourse = new Course("CS101", "Java OOP", 2, "Mon", "10:00 AM", "Room 304");
        Course dataStruct = new Course("CS202", "Data Structures", 25, "Wed", "1:00 PM", "Lab 2");

        csDept.addNewCourse(javaCourse);
        csDept.addNewCourse(dataStruct);

        // Assigning a professor with a full Arabic name
        Teacher profYasser = new Teacher(900, "Dr. Yasser Al-Qahtani", "yasser.q@uni.edu", "055-123-4567", 5500, "Dr", "CS");
        javaCourse.setInstructor(profYasser);
        
        // Let's try giving the professor a bonus check
        profYasser.applyPerformanceBonus(); // Should say not applicable because rank is Dr, not Prof

        // ---------------------------------------------------------
        // Step 2: Input Student Data
        // ---------------------------------------------------------
        System.out.println("\n[Data Entry] Loading student records...");
        
        // Updated Students with authentic full names and matching emails
        Student khalid = new Student(101, "Khalid Al-Mutairi", "khalid.m@uni.edu", "050-111-2222", 3.8, "Software Eng");
        Student fahad = new Student(102, "Fahad Al-Dossari", "fahad.d@uni.edu", "050-333-4444", 2.5, "IT");
        Student omar = new Student(103, "Omar Al-Harbi", "omar.h@uni.edu", "050-555-6666", 3.9, "Cyber Security");

        // ---------------------------------------------------------
        // Step 3: Registration Phase (Testing Logic & Exceptions)
        // ---------------------------------------------------------
        System.out.println("\n[Registration] Processing enrollment requests...");
        
        try {
            // Registering Khalid - Should work
            khalid.registerToCourse(javaCourse);
            
            // Registering Omar - Should work
            omar.registerToCourse(javaCourse);
            
            // Registering Fahad - This should FAIL because CS101 capacity is only 2
            System.out.println("Attempting to register " + fahad.getName() + "...");
            fahad.registerToCourse(javaCourse); 

        } catch (CourseFullException e) {
            System.err.println(">> CRITICAL ALERT: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Unexpected Error: " + e.getMessage());
        } finally {
            System.out.println("[System] Registration transaction closed.");
        }

        // ---------------------------------------------------------
        // Step 4: Reports and Analytics
        // ---------------------------------------------------------
        System.out.println("\n------------------------------------------");
        System.out.println("           FINAL COURSE REPORT            ");
        System.out.println("------------------------------------------");
        System.out.println(javaCourse);

        // Create a list to sort students for the Dean's List
        List<Student> studentList = new ArrayList<>();
        studentList.add(khalid);
        studentList.add(fahad);
        studentList.add(omar);

        // Sorting the list based on the compareTo method (GPA)
        Collections.sort(studentList); 

        // Using our generic print method from Department class
        System.out.println();
        Department.printList(studentList, "Dean's Honor Roll (Sorted by GPA)");

        // ---------------------------------------------------------
        // Step 5: Auditing
        // ---------------------------------------------------------
       System.out.println("\n[Audit] Payroll and Stipend Processing...");
        List<Person> database = new ArrayList<>();
        database.add(profYasser);
        database.add(khalid);
        
        for (Person p : database) {
            System.out.print("Record: " + p.getName());
            
            if (p instanceof Payable) {
                // Downcast using the interface
                Payable payee = (Payable) p; 
                double amount = payee.calculatePayment();
                
                if (p instanceof Teacher) {
                    System.out.println(" [Type: Staff] -> Salary Processed: " + amount + " SR");
                } else {
                    System.out.println(" [Type: Student] -> Stipend Processed: " + amount + " SR");
                }
            }
        }

        System.out.println("\nTotal system records created: " + Person.getTotalPeopleCount());
        System.out.println("==========================================");
    }
}