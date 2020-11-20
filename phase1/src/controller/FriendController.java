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
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
        }
    }

    public void removeFriend(String friendToRemove) {
        try{
            friendManager.RemoveFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
        }
    }

    public void seeFriendList() {
        this.presenter.displayContactList(this.username);
    }
}
