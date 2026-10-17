package model;

import interfaces.Payable;
import interfaces.Registerable;

public class Student extends Person implements Comparable<Student>, Registerable, Payable {

    private double gpa;
    private String major;
    
    // Constant for the standard university stipend 
    private final double MONTHLY_STIPEND = 990.0;

    public Student(int id, String name, String email, String phone, double gpa, String major) {
        super(id, name, email, phone);
        this.gpa = gpa;
        this.major = major;
    }

    public double getGpa() { return gpa; }
    
    public String getMajor() { return major; }

    // Simple check to see if the student is doing well enough to graduate or get honors
    public boolean isEligibleForHonors() {
        return this.gpa >= 3.5; 
    }

    // Logic to sort students. High GPA comes first.
    @Override
    public int compareTo(Student other) {
        return Double.compare(other.gpa, this.gpa);
    }

    @Override
    public void registerToCourse(Course course) throws Exception {
        course.addStudentToClass(this);
    }

    //  Students get a fixed monthly amount
    @Override
    public double calculatePayment() {
        return MONTHLY_STIPEND;
    }

    @Override
    public String toString() {
        return super.toString() + " | Major: " + major + " | GPA: " + gpa;
    }
}