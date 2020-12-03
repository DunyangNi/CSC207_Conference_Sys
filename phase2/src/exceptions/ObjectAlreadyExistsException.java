package exceptions;

public class ObjectAlreadyExistsException extends Exception {

    public ObjectAlreadyExistsException() {
        super("Object already exists.");
    }

    public ObjectAlreadyExistsException(String obj) { super(obj + " already exists."); }
}
