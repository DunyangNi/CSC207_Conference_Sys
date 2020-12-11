package entities.account;

import java.io.Serializable;

/**
 * Represents an account in the conference.
 *
 * Fields:
 * username: represents the account's username, which cannot be changed.
 * password: represents the account's password.
 * lastName: represents the account's user's last name.
 * firstName: represents the account's user's first name.
 */
public class Account implements Serializable {
    private final String username;
    private String password;

    /**
     * Creates an instance of <code>Account</code> with given String information.
     *
     * @param username given username
     * @param password given password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Compares for equality with another object.
     *
     * @param o an object to compare with
     * @return True if o is an instance of Account and has the same username.
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Account) && ((Account) o).getUsername().equals(this.getUsername());
    }

    //------------------------------------------------------------
    // Getters
    //------------------------------------------------------------

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password
     */
    public String getPassword() { return password; }

    //------------------------------------------------------------
    // Setters
    //------------------------------------------------------------

    /**
     * sets password
     * @param password the intended new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
