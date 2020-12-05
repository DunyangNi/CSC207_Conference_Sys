package entities.account;

/**
 * Represents a <code>Organizer Account</code> in the system.
 */
public class Organizer extends Account {

    /**
     * Constructs an instance of <code>Organizer</code> based on Strings of information
     * @param username given username
     * @param password given password
     * @param firstName given first name
     * @param lastName given last name
     */
    public Organizer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
