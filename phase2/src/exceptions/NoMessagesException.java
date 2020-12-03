package exceptions;

public class NoMessagesException extends Exception {
    public NoMessagesException() {
        super("There are no messages to view.");
    }
}
