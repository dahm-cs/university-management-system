package model;

import exceptions.CourseFullException;
import java.util.Vector; 

public class Course {

    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private Teacher instructor;
    
    // Using Vector, safer for shared lists
    private Vector<Student> enrolledStudents; 
    
    // Inner Class:  to keep the schedule details organized together
    public class Schedule {
        String day;
        String time;
        String room;

        public Schedule(String day, String time, String room) {
            this.day = day;
            this.time = time;
            this.room = room;
        }
        
        public String getDetails() {
            return day + " at " + time + " (Room: " + room + ")";
        }
    }
    
    public Schedule classTime; // This is the instance of the inner class

    public Course(String code, String name, int cap, String day, String time, String room) {
        this.courseCode = code;
        this.courseName = name;
        this.maxCapacity = cap;
        this.enrolledStudents = new Vector<>();
        
        // Creating the schedule object inside the constructor
        this.classTime = new Schedule(day, time, room);
    }

    public String getCode() { return courseCode; }

    public void setInstructor(Teacher t) {
        this.instructor = t;
    }

    // This method handles adding students and checking limits
    public void addStudentToClass(Student s) throws CourseFullException {
        // First check: is the course full?
        if (enrolledStudents.size() >= maxCapacity) {
            throw new CourseFullException("Registration failed for " + s.getName() + ": Course " + courseCode + " is full.");
        }
        
        // Second check: is the student already in the list?
        if (enrolledStudents.contains(s)) {
            System.out.println("Note: " + s.getName() + " is already enrolled in this course.");
            return;
        }

        enrolledStudents.add(s);
        System.out.println("Success: " + s.getName() + " has been added to " + courseCode);
    }

    //  in case a student drops the class
    public void removeStudent(Student s) {
        if (enrolledStudents.remove(s)) {
            System.out.println(s.getName() + " was removed from " + courseName);
        } else {
            System.out.println("Could not find student " + s.getName() + " in this course.");
        }
    }

    public Vector<Student> getList() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        String teacherName = (instructor != null) ? instructor.getName() : "TBA";
        return String.format("Course: %s - %s\n   Instructor: %s\n   Time: %s\n   Capacity: %d/%d", 
                courseCode, courseName, teacherName, classTime.getDetails(), enrolledStudents.size(), maxCapacity);
    }
}