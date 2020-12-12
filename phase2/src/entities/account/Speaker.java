package entities.account;

/**
 * Represents a <code>Speaker Account</code> in the system.
 */
public class Speaker extends Account {
    /**
     * Constructs an instance of <code>Speaker</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     */
    public Speaker(String username, String password) {
        super(username, password);
    }
}
