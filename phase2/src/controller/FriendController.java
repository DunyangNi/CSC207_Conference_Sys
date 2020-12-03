package controller;

import exceptions.ConflictException;
import exceptions.ObjectNotFoundException;
import use_cases.FriendManager;
import presenter.Presenter;

import java.util.ArrayList;

public class FriendController {
    protected String username;
    protected FriendManager friendManager;

    /**
     * Instantiates a FriendController which
     * manages friend/contact related functionality for the current user
     * @param username user username
     * @param friendManager manages friendlist functionality
     */
    public FriendController(String username, FriendManager friendManager){
        this.username = username;
        this.friendManager = friendManager;
    }

    /**
     * adds new contact with given username
     * @param friendToAdd username of friend to add
     */
    public void addFriend(String friendToAdd) throws ConflictException, ObjectNotFoundException {
        try {
            friendManager.addFriend(this.username, friendToAdd);
        }
        catch(Exception e) {
            throw e;
        }
    }

    /**
     * removes a friend from contacts list
     * @param friendToRemove friend to remove
     */
    public void removeFriend(String friendToRemove) throws ObjectNotFoundException {
        try{
            friendManager.removeFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            throw e;
        }
    }

    /**
     * displays friend list
     */
    public ArrayList<String> fetchFriendList() {
        return friendManager.getFriendList(username);
    }
}
