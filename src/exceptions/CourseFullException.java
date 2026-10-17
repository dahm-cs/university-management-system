package exceptions;

/**
 * I created this exception to handle cases where a student tries to join 
 * a course that has already reached its maximum capacity.
 */
public class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}