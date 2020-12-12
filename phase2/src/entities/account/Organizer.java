package entities.account;

/**
 * Represents a <code>Organizer Account</code> in the system.
 */
public class Organizer extends Account {
    /**
     * Constructs an instance of <code>Organizer</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     */
    public Organizer(String username, String password) {
        super(username, password);
    }
}
