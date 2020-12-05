package entities.account;

/**
 * Represents a <code>Speaker Account</code> in the system.
 */
public class Speaker extends Account {

    /**
     * Constructs an instance of <code>Speaker</code> based on Strings of information
     * @param username given username
     * @param password given password
     * @param firstName given first name
     * @param lastName given last name
     */
    public Speaker(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
