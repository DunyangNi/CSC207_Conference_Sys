package use_cases.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.conflict.AlreadyContactException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;

/**
 * Represents the entire system of Friend relationships between <code>Accounts</code>.
 */
public class ContactManager implements Serializable {
    private final HashMap<String, ArrayList<String>> contacts;

    /**
     * Creates a <code>ContactManager</code> with a given <code>HashMap</code>
     * of <code>Account</code> to <code>ArrayList</code> of friends.
     *
     * @param contacts given <code>HashMap</code> of Account to ArrayList of friends.
     */
    public ContactManager(HashMap<String, ArrayList<String>> contacts) {
        this.contacts = contacts;
    }

    /**
     * Creates a <code>ContactManager</code> with an empty <code>HashMap</code>.
     */
    public ContactManager() {
        this(new HashMap<>());
    }

    /**
     * Returns an <code>ArrayList</code> of usernames of Accounts that are friends with user.
     *
     * @param user given username
     * @return <code>ArrayList</code> of usernames of Accounts that are friends with user.
     */
    public ArrayList<String> getContactList(String user) {

        return contacts.get(user);
    }

    /**
     * Adds a new key a user of an associated <code>Account</code>.
     *
     * @param account given user of associated <code>Account</code>
     */
    public void addAccountKey(String account) {
        contacts.put(account, new ArrayList<>());
    }

    /**
     * Adds a an account to another account's contact list
     *
     * @param user username of account who wants a new contact
     * @param contactToAdd username of user's new contact
     * @throws AccountNotFoundException if there is no account with the username ContactToAdd
     * @throws AlreadyContactException if contactToAdd is already a contact of user
     */

    public void addContact(String user, String contactToAdd) throws AccountNotFoundException, AlreadyContactException {
        if (!contacts.containsKey(contactToAdd)) throw new AccountNotFoundException();
        if (contacts.get(user).contains(contactToAdd)) throw new AlreadyContactException();
        contacts.get(user).add(contactToAdd);
    }

    /**
     * Removes an account from another account's contactlist
     *
     * @param user username of account who wants less contacts
     * @param contactToRemove username of contact to be removed
     * @throws AccountNotFoundException if there is no account with the username ContactToRemove
     * @throws ContactNotFoundException if ContactToRemove is not a contact of user
     */

    public void removeContact(String user, String contactToRemove) throws AccountNotFoundException,
            ContactNotFoundException {
        if (!contacts.containsKey(contactToRemove)) throw new AccountNotFoundException();
        if (!contacts.get(user).contains(contactToRemove)) throw new ContactNotFoundException();
        contacts.get(user).remove(contactToRemove);
    }
}
