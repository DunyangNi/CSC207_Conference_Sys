package exceptions;

public class ConflictException extends Exception{
    /**
     * This exception is for when a conflict occurs that prevents the program
     * from performing normal function.
     * Examples of conflicts include scheduling conflicts,
     * attempting to create an account with a username that already exists, etc.
     * @param message explanation for the conflict that occured
     */
    public ConflictException(String message) {
        super(message);
    }
}
