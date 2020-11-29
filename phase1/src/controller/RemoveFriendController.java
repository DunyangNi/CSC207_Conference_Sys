package controller;

import Throwables.UserNameNotFoundException;
import Throwables.UserNotFoundException;
import use_cases.FriendManager;
/**
 * The controller that manage operations on use cases class when adding friends.
 */
public class RemoveFriendController extends FriendController{
    /**
     * Instantiates a FriendController which
     * manages friend/contact related functionality for the current user
     *
     * @param username      user username
     * @param friendManager manages friendlist functionality
     */
    public RemoveFriendController(String username, FriendManager friendManager) {
        super(username, friendManager);
    }

    /**
     * removes a friend from contacts list
     * @param friendToRemove friend to remove
     */
    public void removeFriend(String friendToRemove) throws UserNotFoundException, UserNameNotFoundException {
        friendManager.removeFriend(this.username, friendToRemove);

    }
}
