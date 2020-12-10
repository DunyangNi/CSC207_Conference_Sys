package controllers.account;

import exceptions.conflict.AlreadyContactException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import gateways.DataManager;
import use_cases.account.ContactManager;

import java.util.ArrayList;

public class ContactController {
    protected String username;
    protected ContactManager contactManager;

    public ContactController(DataManager dataManager) {
        username = dataManager.getUsername();
        contactManager = dataManager.getContactManager();
    }

    public void addContact(String contactToAdd) throws AccountNotFoundException, AlreadyContactException {
        contactManager.addContact(username, contactToAdd);
    }

    public void removeContact(String contactToRemove) throws AccountNotFoundException, ContactNotFoundException {
        contactManager.removeContact(username, contactToRemove);
    }

    public ArrayList<String> getContactList() {
        return contactManager.getContactList(username);
    }
}
