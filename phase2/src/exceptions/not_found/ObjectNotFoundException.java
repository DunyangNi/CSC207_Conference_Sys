package exceptions.not_found;

/**
 * Thrown when object is not found
 */
public class ObjectNotFoundException extends Exception{
    /**
     * Conflict arises when an object that is being requested does not exist
     */
    public ObjectNotFoundException() {
        super("Object not found.");
    }

    /**
     * Conflict arises when an object that is being searched for does not exist
     * @param obj The object description
     */
    public ObjectNotFoundException(String obj) { super(obj + " not found."); }
}
