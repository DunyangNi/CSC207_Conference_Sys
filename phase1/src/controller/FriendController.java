package controller;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import presenter.Presenter;
import use_cases.FriendManager;

public class FriendController {
    protected String username;
    protected FriendManager friendManager;
    protected Presenter presenter;

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
            System.out.println("We could not find an account with this username on your contact list. Please try again.");
            System.out.println("");
        }
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }
}
