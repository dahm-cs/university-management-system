package model;

/**
 * This is the main parent class. we made it abstract because we shouldn't 
 * just have a "Person" in the system; they need to be a Student or a Teacher.
 */
public abstract class Person implements Cloneable {

    // Basic personal details
    protected int id;
    protected String name;
    protected String email;
    protected String phoneNumber; 

    //  keep track of how many people exist in the database
    private static int totalPeopleCount = 0;



    // Default constructor for setting up an empty object
    public Person() {
        this.id = ++totalPeopleCount;
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
    }

    // Main constructor to set everything up at once
    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        incrementPeopleCount();
    }

    // --- Getters and Setters ---

    private static void incrementPeopleCount() {
        totalPeopleCount++;
    }

    public static int getTotalPeopleCount() {
        return totalPeopleCount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    
    // I added a check here to make sure the email actually looks like an email
    public void setEmail(String email) { 
        if(email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println("Warning: Invalid email format for " + this.name);
            this.email = "invalid@error.com";
        }
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Contact: " + email;
    }

    // We compare people by ID, 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
             Person other = (Person) obj;
             return this.id == other.id;
        }
        return false;
    }

    // The finalize method is called when the garbage collector deletes the object.
    @Override
    protected void finalize() throws Throwable {
        System.out.println("System Notification: Cleaning up memory resources for " + this.name);
        super.finalize();
    }

    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            return null; 
        }
    }
}