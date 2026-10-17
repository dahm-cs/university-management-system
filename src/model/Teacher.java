package model;

import interfaces.Payable;

// I used an Enum here to restrict the ranks to specific types
enum Rank {
    LECTURER,
    ASSISTANT_PROF,
    PROFESSOR
}

public class Teacher extends Person implements Payable {

    private double salary;
    private Rank rank; 
    private String departmentName;

    public Teacher(int id, String name, String email, String phone, double salary, String rankStr, String dept) {
        super(id, name, email, phone);
        this.salary = salary;
        this.departmentName = dept;
        
        // Logic to convert the string input into our Enum type
        if (rankStr.equalsIgnoreCase("Prof")) {
            this.rank = Rank.PROFESSOR;
        } else if (rankStr.equalsIgnoreCase("Dr")) {
            this.rank = Rank.ASSISTANT_PROF;
        } else {
            this.rank = Rank.LECTURER;
        }
    }

    // Implementing the Payable interface
    @Override
    public double calculatePayment() {
        return salary;
    }

    // A method to give a bonus, but only if they are a Professor
    public void applyPerformanceBonus() {
        if (this.rank == Rank.PROFESSOR) {
            this.salary += 1000;
            System.out.println("Bonus applied for Professor " + this.name);
        } else {
            System.out.println("Bonus not applicable for " + this.name + " (Must be Professor)");
        }
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return super.toString() + " | Role: " + rank + " | Dept: " + departmentName + " | Salary: $" + salary;
    }
}