package controller;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import use_cases.FriendManager;
import presenter.Presenter;

public class FriendController {
    protected String username;
    protected FriendManager friendManager;
    protected Presenter presenter;

    /**
     * Instantiates a FriendController which
     * manages friend/contact related functionality for the current user
     * @param username user username
     * @param friendManager manages friendlist functionality
     * @param presenter specifies the UI
     */
    public FriendController(String username, FriendManager friendManager, Presenter presenter){
        this.username = username;
        this.friendManager = friendManager;
        this.presenter = presenter;
    }

    /**
     * adds new contact with given username
     * @param friendToAdd username of friend to add
     */
    public void addFriend(String friendToAdd) {
        try {
            friendManager.addFriend(this.username, friendToAdd);
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
        }
    }

    /**
     * removes a friend from contacts list
     * @param friendToRemove friend to remove
     */
    public void removeFriend(String friendToRemove) {
        try{
            friendManager.removeFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
        }
    }

    /**
     * displays friend list
     */
    public void seeFriendList() {
        this.presenter.displayContactList(this.username);
    }
}
