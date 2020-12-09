package entities.account;

public class Vip extends Attendee{

    /**
     * Constructs an instance of <code>Attendee</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     * @param firstName given first name
     * @param lastName given last name
     */
    public Vip(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
