package views.account;

import controllers.account.ContactController;
import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import presenters.account.ContactPresenter;
import use_cases.account.ContactManager;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactView {
    private final ContactManager contactManager;
    private final ContactController controller;
    private final ContactPresenter presenter = new ContactPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public ContactView(DataManager dm) {
        this.contactManager = dm.getContactManager();
        this.controller = new ContactController(dm);
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