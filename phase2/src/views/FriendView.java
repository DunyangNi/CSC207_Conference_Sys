package views;

import controllers.FriendController;
import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.UserNotFoundException;
import presenters.FriendPresenter;
import use_cases.FriendManager;

import java.util.ArrayList;
import java.util.Scanner;

public class FriendView {
    private final FriendManager friendManager;
    private final FriendController controller;
    private final FriendPresenter presenter = new FriendPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public FriendView(String username, FriendManager fm) {
        this.friendManager = fm;
        this.controller = new FriendController(username,fm);
    }

    public void viewAddFriendMenu() {
        presenter.addContactPrompt();
        String username = userInput.nextLine();
        try {
            controller.addFriend(username);
        } catch (UserNotFoundException | AlreadyFriendException | FriendNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void viewRemoveFriendMenu() {
        presenter.removeContactPrompt();
        String username = userInput.nextLine();
        try {
            controller.removeFriend(username);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void viewFriendList() {
        presenter.contactListPrompt();
        ArrayList<String> friendList = controller.fetchFriendList();
        if (friendList.isEmpty()) {
            presenter.emptyContactListPrompt();
        }
        else {
            for (String friend: friendList) {
                presenter.friendPrompt(friend);
            }
        }
    }
}
