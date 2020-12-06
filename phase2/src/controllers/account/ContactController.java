package controllers.account;

import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import use_cases.account.ContactManager;

import java.util.ArrayList;

public class ContactController {
    protected String username;
    protected ContactManager fm;

    /**
     * Instantiates a ContactController which
     * manages friend/contact related functionality for the current user
     * @param dm
     */
    public ContactController(DataManager dm){
        this.username = dm.getUsername();
        this.fm = dm.getContactManager();
    }

    /**
     * adds new contact with given username
     * @param friendToAdd username of friend to add
     */
    public void addFriend(String friendToAdd) throws UserNotFoundException, FriendNotFoundException, AlreadyFriendException {
        fm.addFriend(this.username, friendToAdd);
    }

    /**
     * removes a friend from contacts list
     * @param friendToRemove friend to remove
     */
    public void removeFriend(String friendToRemove) throws ObjectNotFoundException {
        try{
            fm.removeFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            throw e;
        }
    }

    /**
     * displays friend list
     */
    public ArrayList<String> fetchFriendList() {
        return fm.getFriendList(username);
    }
}
