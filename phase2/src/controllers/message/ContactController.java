package controllers.message;

import exceptions.conflict.AlreadyContactException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import gateways.DataManager;
import use_cases.message.ContactManager;

import java.util.ArrayList;

/**
 * A controller class responsible for adding/removing contacts
 */
public class ContactController {
    protected String username;
    protected ContactManager contactManager;

    /**
     * Creates an instance of <code>ContactController</code> with given parameters.
     *
     * @param dataManager a Datamanager storing the desired ContactManager
     */
    public ContactController(DataManager dataManager) {
        username = dataManager.getUsername();
        contactManager = dataManager.getContactManager();
    }

    /**
     * Attempts to add a contact with username contactToAdd
     *
     * @param contactToAdd username of desired contact to add
     * @throws AccountNotFoundException contactToAdd is an invalid username
     * @throws AlreadyContactException  contactToAdd is already a contact
     */
    public void addContact(String contactToAdd) throws AccountNotFoundException, AlreadyContactException {
        contactManager.addContact(username, contactToAdd);
    }

    /**
     * Attempts to remove a contact with username contactToRemove
     *
     * @param contactToRemove username of desired contact to remove
     * @throws AccountNotFoundException contactToRemove is an invalid username
     * @throws ContactNotFoundException contactToRemove is not a contact
     */
    public void removeContact(String contactToRemove) throws AccountNotFoundException, ContactNotFoundException {
        contactManager.removeContact(username, contactToRemove);
    }

    /**
     * @return List of contacts of the current account (field username)
     */
    public ArrayList<String> getContactList() {
        return contactManager.getContactList(username);
    }
}
