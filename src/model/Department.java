package model;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Department {

    private String deptName;
    private String headOfDept;
    
    // Using a TreeSet so the courses stay sorted automatically
    private Set<Course> courseList;

    public Department(String name, String head) {
        this.deptName = name;
        this.headOfDept = head;
        // Sorting by course code
        this.courseList = new TreeSet<>(Comparator.comparing(Course::getCode));
    }

    public void addNewCourse(Course c) {
        courseList.add(c);
    }
    
    // A Generic method to print any type of list (Students, Teachers, Strings, etc.)
    public static <T> void printList(List<T> list, String title) {
        System.out.println("--- " + title + " ---");
        if (list == null || list.isEmpty()) {
            System.out.println("   [List is empty]");
            return;
        }
        for (T item : list) {
            System.out.println("   -> " + item.toString());
        }
    }

    @Override
    public String toString() {
        return "Department: " + deptName + " | Head: " + headOfDept + " | Total Courses: " + courseList.size();
    }
}