package interfaces;

import model.Course;

public interface Registerable {
    /**
     * This forces any "registerable" person (like a student) to have 
     * a method for signing up to courses.
     */
    void registerToCourse(Course course) throws Exception;
}