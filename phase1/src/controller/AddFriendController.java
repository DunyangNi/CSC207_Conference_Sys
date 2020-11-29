package controller;

import Throwables.*;
import use_cases.FriendManager;

/**
 * The controller that manage operations on use cases class when adding friends.
 */
public class AddFriendController extends FriendController{

    /**
     * Instantiates a FriendController which
     * manages friend/contact related functionality for the current user
     *
     * @param username      user username
     * @param friendManager manages friendlist functionality
     */
    public AddFriendController(String username, FriendManager friendManager) {
        super(username, friendManager);
    }

    /**
     * Instantiates a FriendController which
     * manages friend/contact related functionality for the current user
     * @param username user username
     * @param friendManager manages friendlist functionality
     */


    /**
     * adds new contact with given username
     * @param friendToAdd username of friend to add
     */
    public void addFriend(String friendToAdd) throws UserNotFoundException, AlreadyExistException, UserNameNotFoundException {

        friendManager.addFriend(this.username, friendToAdd);

    }
}
