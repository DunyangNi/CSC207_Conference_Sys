package controller;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import use_cases.FriendManager;
import presenter.Presenter;

public class FriendController {
    protected String username;
    protected FriendManager friendManager;
    protected Presenter presenter;

    public FriendController(String username, FriendManager friendManager, Presenter presenter){
        this.username = username;
        this.friendManager = friendManager;
        this.presenter = presenter;
    }

    public void addFriend(String friendToAdd) {
        try {
            friendManager.AddFriend(this.username, friendToAdd);
        }
        catch(ObjectNotFoundException e) {
            System.out.println("");
            System.out.println("We could not find an account with this username. Please try again.");
            System.out.println("");
        }
        catch(ConflictException e) {
            System.out.println("");
            System.out.println(e.getMessage());
            System.out.println("");
        }
    }

    public void removeFriend(String friendToRemove) {
        try{
            friendManager.RemoveFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            System.out.println("");
            System.out.println("We could not find an account with this username on your contact list. " +
                    "Please try again.");
            System.out.println("");
        }
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }
}
