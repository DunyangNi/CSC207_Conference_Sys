package exceptions;
/**
    Thrown when we are doing operations to a list of object and the list is empty
 */
public class NoRecipientsException extends Exception {
    public NoRecipientsException(String operation) {
        super("There are no recipients " + operation + ".");
    }
}
