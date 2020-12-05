package controllers;

import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.UserNotFoundException;
import use_cases.ContactManager;

import java.util.ArrayList;

public class ContactController {
    protected String username;
    protected ContactManager contactManager;

    /**
     * Instantiates a ContactController which
     * manages friend/contact related functionality for the current user
     * @param username user username
     * @param contactManager manages friendlist functionality
     */
    public ContactController(String username, ContactManager contactManager){
        this.username = username;
        this.contactManager = contactManager;
    }

    /**
     * adds new contact with given username
     * @param friendToAdd username of friend to add
     */
    public void addFriend(String friendToAdd) throws UserNotFoundException, FriendNotFoundException, AlreadyFriendException {
        contactManager.addFriend(this.username, friendToAdd);
    }

    /**
     * removes a friend from contacts list
     * @param friendToRemove friend to remove
     */
    public void removeFriend(String friendToRemove) throws ObjectNotFoundException {
        try{
            contactManager.removeFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            throw e;
        }
    }

    /**
     * displays friend list
     */
    public ArrayList<String> fetchFriendList() {
        return contactManager.getFriendList(username);
    }
}
