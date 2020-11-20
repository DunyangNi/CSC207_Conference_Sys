package entities;

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
    private String lastName;
    private String firstName;

    /**
     * Creates an account with the specified username, password, and first and last names.
     */
    public Account(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    /**
     * @return lastName
     */
    public String getLastName() { return lastName; }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

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

    /**
     * sets lastName
     * @param lastName the intended new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * sets firstName
     * @param firstName the intended new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
