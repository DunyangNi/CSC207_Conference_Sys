package controller;

import use_cases.FriendManager;

/**
 * The abstract class storing the constructor. This class is the parent class for
 * all controllers that operate contact list.
 */
public abstract class FriendController {
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
}
