package Throwables;

public class ObjectNotFoundException extends Exception{
    public ObjectNotFoundException() {
        super("Object not found.");
    }

    public ObjectNotFoundException(String obj) { super(obj + " not found."); }
}
