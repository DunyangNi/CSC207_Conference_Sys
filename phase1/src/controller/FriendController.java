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
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("We could not find an account with this username. Please try again.");
            this.presenter.displayPrompt("");
        }
        catch(ConflictException e) {
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("This account is already on your contact list.");
            this.presenter.displayPrompt("");
        }
    }

    public void removeFriend(String friendToRemove) {
        try{
            friendManager.RemoveFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("We could not find an account with this username on your contact list. " +
                    "Please try again.");
            this.presenter.displayPrompt("");
        }
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }
}
